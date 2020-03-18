package ro.project.planet.rest;

public interface RestClient<P> {
    P getById(long id);
}
