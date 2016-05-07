package teamrtg.rtg.api.biome;

import teamrtg.rtg.world.biome.surface.part.SurfacePart;

/**
 * @author topisani
 */
public interface IHasSurface {

    SurfacePart initSurface();

    SurfacePart getSurface();
}
