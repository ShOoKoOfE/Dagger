package com.example.dagger;

import javax.inject.Singleton;

import dagger.Module;
        import dagger.Provides;
/*
Module class is responsible for providing objects which can be injected,
In this example we want to inject Motor class to Vehicle class and
inject Vehicle class to MainActivity
so  we should create MyModule to providing these instances.
@Provide annotation: returned object from this method is available for dependency injection.
 */
@Module
class MyModule {

    @Provides
    @Singleton
    Motor provideMotor() {
        return new Motor();
    }

    @Provides
    @Singleton
    Vehicle provideVehicle() {
        return new Vehicle(new Motor());
    }
}