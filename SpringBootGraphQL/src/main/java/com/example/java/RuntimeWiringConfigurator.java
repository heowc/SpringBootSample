
package com.example.java;

import graphql.schema.idl.RuntimeWiring;

@FunctionalInterface
interface RuntimeWiringConfigurator {

    void configure(RuntimeWiring.Builder builder);
}