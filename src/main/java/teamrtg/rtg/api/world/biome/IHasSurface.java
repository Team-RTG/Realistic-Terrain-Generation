package teamrtg.rtg.api.world.biome;

import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;

/**
 * @author topisani
 */
public interface IHasSurface {

    SurfacePart initSurface();

    SurfacePart getSurface();
}
