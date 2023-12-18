package rtg.api.world.gen.feature.tree.rtg;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

class SkylightTracker {
	
	private final int distance;
	private final int width;
	private final int [] [] directOpacity; // the light from straight up
	private final int [] [] opacity; // best available light
	private final int [] [] lowest; // lowest affected block
	private final OrthogonalDirection [] [] lightDirection;// from which direction the best light came

	private final boolean [] [] relighting; // tracks what's already being relit so we don't make multiple attempts
	private final OrthogonalDirection [] [] backTrack;// use to record what was relit on an attempted place
	
	private final boolean [] [] trunk;  // indicating where dark blocks go to the ground and light cannot pass underneath.
	private final BlockPos base;

	int tolerableObstruction = 4;
	private int hopelesslyDark = 15;
	
	private int lightingHeight = 50; // check this many blocks up for light obstructions
	
	
	SkylightTracker( int distance, BlockPos base, World world) {
		
		// add on some distance for tracking 
		distance += 4;
		this.distance  = distance;
		this.base = base;
		width = distance * 2 + 1;// this creates a square array for all columns up to distance in either direction;
		opacity = new int [width][width];
		lowest = new int [width][width];
		
		directOpacity = new int [width][width];
		lightDirection = new OrthogonalDirection [width][width];
		relighting = new boolean [width] [width];
		backTrack = new OrthogonalDirection [width][width];
		trunk = new boolean [width] [width];
		for (int x = 0; x < width; x++) {
			for (int z = 0; z < width; z++) {
				lightDirection [x][z] = OrthogonalDirection.CENTER;
				backTrack [x][z] = OrthogonalDirection.CENTER;
				lowest [x][z] = 255;
			}
		}
		setLighting(world);
		initialSpread();
	}
	SkylightTracker( int distance, BlockPos base, World world, int tolerableObstruction) {
		
		this(distance,base,world);
		this.tolerableObstruction = tolerableObstruction;
	}
	
	private final BlockPos baseAt(int x, int z) {
		return new BlockPos(base.getX()-distance + x, base.getY(),base.getZ()-distance + z);
	}
	private final class Spotlight {
		
		int x;
		int z;
		boolean givingUp;
		int passedOpacity;
		int worstOpacity = 0;
		int totalBackTracks = 0;
		boolean interesting = false;
		
		Spotlight() {}
		
		Spotlight(int x, int z) {
			this();
			set(x,z);
		}	
		
		Spotlight(BlockPos location) {

			x = location.getX() - base.getX() + distance;
			z = location.getZ() - base.getZ() + distance;
		}
		
		void set(int x, int z) {
			this.x = x;
			this.z = z;
		}
		
		boolean legal() {return (x>0)&&(x<width)&&(z>0)&&(z<width);}
		
		int opacity() {return opacity [x][z];}
		
		void setOpacity(int newLighting) {
			opacity [x][z] = newLighting;
	    }
		
		int directOpacity() {return directOpacity [x][z];}
		
		boolean relighting() {return relighting [x][z];}
		
		void setRelighting(boolean state) {relighting [x][z] = state;}
		
		OrthogonalDirection direction() {return lightDirection [x][z];}
		
		OrthogonalDirection backTrack() {return backTrack [x][z];}
		
		void setBackTrack(OrthogonalDirection comingFrom) {
			backTrack [x][z] = comingFrom;
			totalBackTracks++;
		}		
		
		void clearBackTrack() {
			backTrack [x][z] = OrthogonalDirection.CENTER;
			totalBackTracks--;
		}
		
		void setDirection(OrthogonalDirection newDirection) {
			lightDirection [x][z] = newDirection;
		}
		
		boolean trunk() {return trunk [x] [z];}
		
		void setTrunk() {
			trunk [x] [z] = true;
			}		
		void setTrunk(boolean value) {
			trunk [x] [z] = value;
		}
		
		final void move(OrthogonalDirection direction) {
			switch(direction)  {
			case XDOWN: {
				x--; break;
			    }
			case ZDOWN: {
				z--; break;
		        }
			case XUP: {
				x++; break;
		        }
		    case ZUP: {
			    z++; break;
	        }
	        }
		}
		
		final void darken(OrthogonalDirection comingFrom) {
			this.setRelighting(true);
			this.setBackTrack(comingFrom);
			setOpacity(directOpacity());
			if (opacity()>passedOpacity) setOpacity(passedOpacity);
			
			// darken everything around that is getting light from this position and not already being relit
			move(OrthogonalDirection.XDOWN);
			if (legal()&&!relighting()&&direction()==OrthogonalDirection.XUP&&!trunk()) {
				// need to relight
				this.passedOpacity ++;
				darken(OrthogonalDirection.XUP);
				passedOpacity --;
			}
			move(OrthogonalDirection.XUP);
			
			move(OrthogonalDirection.ZDOWN);
			if (legal()&&!relighting()&&direction()==OrthogonalDirection.ZUP&&!trunk()) {
				// need to relight
				this.passedOpacity ++;
				darken(OrthogonalDirection.ZUP);
				passedOpacity --;
			}
			move(OrthogonalDirection.ZUP);
			
			move(OrthogonalDirection.XUP);
			if (legal()&&!relighting()&&direction()==OrthogonalDirection.XDOWN&&!trunk()) {
				// need to relight
				this.passedOpacity ++;
				darken(OrthogonalDirection.XDOWN);
				passedOpacity --;
			}
			move(OrthogonalDirection.XDOWN);
			
			move(OrthogonalDirection.ZUP);
			if (legal()&&!relighting()&&direction()==OrthogonalDirection.ZDOWN&&!trunk()) {
				// need to relight
				this.passedOpacity ++;
				darken(OrthogonalDirection.ZDOWN);
				passedOpacity --;
			}
			move(OrthogonalDirection.ZDOWN);
			
		}
		
		final void relight(OrthogonalDirection comingFrom) {

			// relight everything around that was getting light from this position and not already being relit
			move(OrthogonalDirection.XDOWN);
			if (legal()&&relighting()&&direction()==OrthogonalDirection.XUP) {
				// need to relight
				this.passedOpacity ++;
				relight(OrthogonalDirection.XUP);
				passedOpacity --;
			}
			move(OrthogonalDirection.XUP);
			
			move(OrthogonalDirection.ZDOWN);
			if (legal()&&relighting()&&direction()==OrthogonalDirection.ZUP) {
				// need to relight
				this.passedOpacity ++;
				relight(OrthogonalDirection.ZUP);
				passedOpacity --;
			}
			move(OrthogonalDirection.ZUP);
			
			move(OrthogonalDirection.XUP);
			if (legal()&&relighting()&&direction()==OrthogonalDirection.XDOWN) {
				// need to relight
				this.passedOpacity ++;
				relight(OrthogonalDirection.XDOWN);
				passedOpacity --;
			}
			move(OrthogonalDirection.XDOWN);
			
			move(OrthogonalDirection.ZUP);
			if (legal()&&relighting()&&direction()==OrthogonalDirection.ZDOWN) {
				// need to relight
				this.passedOpacity ++;
				relight(OrthogonalDirection.ZDOWN);
				passedOpacity --;
			}
			move(OrthogonalDirection.ZDOWN);
			// spread from adjacent areas
			this.setRelighting(false);
			
			spreadFrom(OrthogonalDirection.XUP);
			spreadFrom(OrthogonalDirection.ZUP);
			spreadFrom(OrthogonalDirection.XDOWN);
			spreadFrom(OrthogonalDirection.ZDOWN);
			spread(this);
			
			if (opacity() >= directOpacity()) {
				this.setDirection(OrthogonalDirection.CENTER);
				this.setOpacity(directOpacity());
			} 
	        if (opacity() >= passedOpacity) {
				this.setDirection(comingFrom);
				this.setOpacity(passedOpacity);
			}
	        
			
			
		}
		private void spreadFrom(OrthogonalDirection tested) {
			move(tested);
			if (legal()) {
				spread(this);
			}
			move(tested.opposite());
		}
		
		void setWorstOpacity(int newValue) {
			worstOpacity = newValue;
		}
		
		void checkBacktrackedOpacity() {
			checkOpacityTowards(OrthogonalDirection.XUP);
			checkOpacityTowards(OrthogonalDirection.ZUP);
			checkOpacityTowards(OrthogonalDirection.XDOWN);
			checkOpacityTowards(OrthogonalDirection.ZDOWN);
			if (!trunk()) { // for a trunk dark is acceptable since spawning is prevented physically.
				if (this.opacity()>worstOpacity) setWorstOpacity(opacity());
			}
			
		}
		
		void checkOpacityTowards(OrthogonalDirection towards) {
			move(towards);
			if (legal()) {
			    if (backTrack()==towards.opposite()) {
				    checkBacktrackedOpacity();
				    // clear backTrack
				    clearBackTrack();
			    } else {
			    	if (backTrack() != OrthogonalDirection.CENTER) {
			    	}
			    }
			}
			move(towards.opposite());
		}
		

		private void chooseLight() {
			int bestLight = this.directOpacity();
			OrthogonalDirection  bestDirection = OrthogonalDirection.CENTER;
			move(OrthogonalDirection.XUP);
			if (this.opacity()+1< bestLight) {
				bestLight = this.opacity()+1;
				bestDirection = OrthogonalDirection.XUP;
			}
			move(OrthogonalDirection.XDOWN);
			
			move(OrthogonalDirection.XDOWN);
			if (this.opacity()+1< bestLight) {
				bestLight = this.opacity()+1;
				bestDirection = OrthogonalDirection.XDOWN;
			}
			move(OrthogonalDirection.XUP);
			
			move(OrthogonalDirection.ZUP);
			if (this.opacity()+1< bestLight) {
				bestLight = this.opacity()+1;
				bestDirection = OrthogonalDirection.ZUP;
			}
			move(OrthogonalDirection.ZDOWN);
			move(OrthogonalDirection.ZDOWN);
			if (this.opacity()+1< bestLight) {
				bestLight = this.opacity()+1;
				bestDirection = OrthogonalDirection.ZDOWN;
			}
			move(OrthogonalDirection.ZUP);
		}
		
		public String toString() {
			if (legal()) {
			return "" + x + ','  +  z + " relighting " + relighting() + " opacity " + opacity() + " direct "+ directOpacity() + " trunk " + trunk () + '\r';
			} else return "Out of bounds";
			
		}
	}
	
	public boolean isLog(IBlockState blockState) {
		if (blockState.getBlock() == Blocks.LOG) return true;
		if (blockState.getBlock() == Blocks.LOG2) return true;
		return false;
	}
	
	private void setLighting(World world) {
		for (int x = 0; x < width; x ++) {
			for (int z = 0; z < width; z ++) {
				BlockPos columnBase = this.baseAt(x, z);
				int obstruction = 0;
				if (world.isBlockLoaded(columnBase)) {
					// otherwise we will assume no obstructions 
					int up = 0;
					// skip up through opaque blocks at bottom
					while (up < this.lightingHeight) {
						IBlockState examined = world.getBlockState(columnBase.up(up));
						if (!examined.isOpaqueCube()) break;
						if (isLog(examined)&& up>5) {
							obstruction = 255;
							this.trunk[x][z] = true;
							break;
						}
						up ++;
					}
					while (up < this.lightingHeight) {
						IBlockState examined = world.getBlockState(columnBase.up(up));
						if (examined.isOpaqueCube()) {
							obstruction = 255;
							break;// we're done, it's dark
						}
						obstruction += examined.getLightOpacity();
						if (obstruction > 14) break;// we're done, it's dark
						up ++;
					}
				}
				this.directOpacity [x][z] = obstruction;
			}
		}
		
	}
	
	private void initialSpread() {
		// start with direct lighting;
		for (int x = 0; x < width; x ++) {
			for (int z = 0; z < width; z ++) {
				opacity [x][z] = directOpacity [x][z];
			}
		}
		Spotlight spotlight = new Spotlight();
		for (int x = 0; x < width; x ++) {
			for (int z = 0; z < width; z ++) {
				spotlight.set(x,z);
				spread(spotlight);
			}
		}
	}
	
	private void spread (Spotlight spotlight) {
		if (spotlight.opacity() > spotlight.directOpacity()) {
			spotlight.setOpacity(spotlight.directOpacity());
			spotlight.setDirection(OrthogonalDirection.CENTER);
		}
		int spotLighting = spotlight.opacity();
		int oldX = spotlight.x;
		int oldZ = spotlight.z;
		int dimmed = spotLighting +1;
		if (dimmed>15) return; // too dark to make a difference.
		// check x down
		spotlight.x--;
		if (spotlight.legal()&&!spotlight.relighting()&&!spotlight.trunk()) {
			if (spotlight.opacity()>dimmed) {// higher numbers are darker
				spotlight.setOpacity(dimmed);
				spotlight.setDirection(OrthogonalDirection.XUP);// opposite because it points to where the lighting came from
				spread(spotlight);
			}
		}
		spotlight.x++;
		// check z down
		spotlight.z--;
		if (spotlight.legal()&&!spotlight.relighting()&&!spotlight.trunk()) {
			if (spotlight.opacity()>dimmed) {// higher numbers are darker
				spotlight.setOpacity(dimmed);
				spotlight.setDirection(OrthogonalDirection.ZUP);// opposite because it points to where the lighting came from
				spread(spotlight);
			}
		}
		spotlight.z++;
		

		// check x up
		spotlight.x++;
		if (spotlight.legal()&&!spotlight.relighting()&&!spotlight.trunk()) {
			if (spotlight.opacity()>dimmed) {// higher numbers are darker
				spotlight.setOpacity(dimmed);
				spotlight.setDirection(OrthogonalDirection.XDOWN);// opposite because it points to where the lighting came from
				spread(spotlight);
			}
		}
		spotlight.x--;
		// check z up
		spotlight.z++;
		if (spotlight.legal()&&!spotlight.relighting()&&!spotlight.trunk()) {
			if (spotlight.opacity()>dimmed) {// higher numbers are darker
				spotlight.setOpacity(dimmed);
				spotlight.setDirection(OrthogonalDirection.ZDOWN);// opposite because it points to where the lighting came from
				spread(spotlight);
			}
		}
		spotlight.z--;
			
		// debugging check
		if (oldX != spotlight.x) throw new RuntimeException();
		if (oldZ != spotlight.z) throw new RuntimeException();
		
	}
	
	boolean testPlace (World world, BlockPos location, IBlockState placed, int generateFlags) {
		int baseX = location.getX() - base.getX() + distance;
		int baseZ = location.getZ() - base.getZ() + distance;
		Spotlight spotlight = new Spotlight(baseX,baseZ);
		if (!inArray(baseX,baseZ)) return true;// if outside of examined area ignore.
		// make the change temporarily to test. May need to be undone before exiting procedure;
		int oldDirectOpacity = directOpacity [baseX][baseZ] ;
		directOpacity [baseX][baseZ] += placed.getLightOpacity();
		// max 255
		if (directOpacity [baseX][baseZ] >255)   directOpacity [baseX][baseZ] = 255;
		if (this.lightDirection [baseX][baseZ] != OrthogonalDirection.CENTER&&!spotlight.trunk()) {
			// this spot is lit from elsewhere, and will continue to be, so direct sky light doesn't matter
			world.setBlockState(location, placed, generateFlags);
			if (lowest[baseX][baseZ]>location.getY()) lowest[baseX][baseZ] =location.getY();
			return true;
		}
		// have to deal with interactions;
		spotlight.passedOpacity = spotlight.directOpacity();
		spotlight.setOpacity(spotlight.directOpacity());
		spotlight.darken(OrthogonalDirection.CENTER);// important: must run a relight check if we have changed this to trunk
		if ((spotlight.x != baseX)||(spotlight.z != baseZ)) throw new RuntimeException();  // testing the returns were right.
		spotlight.relight(OrthogonalDirection.CENTER);
		if ((spotlight.x != baseX)||(spotlight.z != baseZ)) throw new RuntimeException();  // testing the returns were right.
		
		// now find worst opacity in the relit area, clearing backtrack
		spotlight.worstOpacity = 0;
		spotlight.checkBacktrackedOpacity();
		spotlight.clearBackTrack();
		if (spotlight.worstOpacity <= this.tolerableObstruction) {
			//good to go!			
			world.setBlockState(location, placed, generateFlags);
			if (lowest[baseX][baseZ]>location.getY()) lowest[baseX][baseZ] =location.getY();
			return true;
		}
		// too dark
		directOpacity [baseX][baseZ] =oldDirectOpacity;
		lightDirection [baseX][baseZ] = OrthogonalDirection.CENTER;// had to be to try relighting.
		// have to relight
	    this.spread(spotlight);
		return false;
	}
	
	
	boolean debugTestPlace (World world, BlockPos location, IBlockState placed, int generateFlags) {
		int baseX = location.getX() - base.getX() + distance;
		int baseZ = location.getZ() - base.getZ() + distance;
		Spotlight spotlight = new Spotlight(baseX,baseZ);
		if (!inArray(baseX,baseZ)) return true;// if outside of examined area ignore.
		// make the change temporarily to test. May need to be undone before exiting procedure;
		int oldDirectOpacity = directOpacity [baseX][baseZ] ;
		directOpacity [baseX][baseZ] += placed.getLightOpacity();
		// max 255
		if (directOpacity [baseX][baseZ] >255)   directOpacity [baseX][baseZ] = 255;
		if (this.lightDirection [baseX][baseZ] != OrthogonalDirection.CENTER&&!spotlight.trunk()) {
			// this spot is lit from elsewhere, and will continue to be, so direct sky light doesn't matter
			world.setBlockState(location, placed, generateFlags);
			if (lowest[baseX][baseZ]>location.getY()) lowest[baseX][baseZ] =location.getY();
			return true;
		}
		// have to deal with interactions;
		spotlight.passedOpacity = spotlight.directOpacity();
		spotlight.setOpacity(spotlight.directOpacity());
		spotlight.darken(OrthogonalDirection.CENTER);// important: must run a relight check if we have changed this to trunk
		if ((spotlight.x != baseX)||(spotlight.z != baseZ)) throw new RuntimeException();  // testing the returns were right.
		spotlight.relight(OrthogonalDirection.CENTER);
		if ((spotlight.x != baseX)||(spotlight.z != baseZ)) throw new RuntimeException();  // testing the returns were right.
		
		// now find worst opacity in the relit area, clearing backtrack
		spotlight.worstOpacity = 0;
		spotlight.checkBacktrackedOpacity();
		spotlight.clearBackTrack();
		if (spotlight.worstOpacity <= this.tolerableObstruction) {
			//good to go!			
			world.setBlockState(location, placed, generateFlags);
			if (lowest[baseX][baseZ]>location.getY()) lowest[baseX][baseZ] =location.getY();
			return true;
		}
		// too dark
		directOpacity [baseX][baseZ] =oldDirectOpacity;
		lightDirection [baseX][baseZ] = OrthogonalDirection.CENTER;// had to be to try relighting.
		// have to relight
	    this.spread(spotlight);
		return false;
	}
	
	boolean testTrunk(World world, BlockPos location, IBlockState placed, int generateFlags) {
		Spotlight place  = new Spotlight(location);
		if (place.trunk()) {
			// already trunk; go ahead;
			return testPlace(world, location, placed, generateFlags);
		} else {
			// have to test place
			place.setTrunk();
			boolean successful = debugTestPlace(world, location, placed, generateFlags);;
			if (!successful) {
				place.setTrunk(false);
				place.chooseLight();
				spread(place);
		    } else {
		    	// set opacity to dark
		    	place.setOpacity(255);
		    }
			return successful;
		}
	}
	
	public void setTrunk(BlockPos set) {
		new Spotlight(set).setTrunk();
	}

	public void setNotTrunk(BlockPos set) {
		new Spotlight(set).setTrunk(false);
	}
	
	private boolean inArray(int x, int z) {
		return ((x>-1) && (x<width) && (z>-1) && (z<width));
	}
	
	public void checkLighting(World world) {
		for (int localX = 0; localX < width; localX++) {
			for (int localZ = 0; localZ < width; localZ++) {
				if ((opacity[localX][localZ] ==0)||(opacity[localX][localZ] >15)) continue; // either unaffected or hopelessly dark
				//scan to bottom
				boolean looking = true; 
				int y = lowest[localX][localZ] - 1;
				if (y>=254) continue; // this tree didn't place any blocks there
				while (y>62) {
					BlockPos position = new BlockPos(base.getX()-distance + localX, y,base.getZ()-distance + localZ);
					if (world.getBlockState(position).getLightOpacity()>10) {
						if (looking) {
							BlockPos oldPosition = new BlockPos(base.getX()-distance + localX, y+1,base.getZ()-distance + localZ);
							world.checkLight(position);
						}
						looking = false;
					} else {
						looking = true;
					}
					y--;
				}
			}
		}
	}
	
    public enum OrthogonalDirection {
    	CENTER,
        XDOWN,
        ZDOWN,
        XUP,
        ZUP;
        
        public OrthogonalDirection opposite() {
        	switch(this)  {
			case XDOWN: {
				return XUP;
			    }
			case ZDOWN: {
				return ZUP;
		        }
			case XUP: {
				return XDOWN;
		        }
		    case ZUP: {
			    return ZDOWN;
	            }
    		default: {
    			return CENTER;
    		    }
    	    }
        }
    }
}