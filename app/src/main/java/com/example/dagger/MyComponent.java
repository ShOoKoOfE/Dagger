package com.example.dagger;

import javax.inject.Singleton;

import dagger.Component;
/*
Dagger2 needs component interface to know how should it create instances from our classes.
@Component interface: connection between the provider of object and the objects which express a dependency.
 */
@Singleton
@Component(modules = {MyModule.class})
interface MyComponent {
    Vehicle provideVehicle();

    void inject(MainActivity main);
}