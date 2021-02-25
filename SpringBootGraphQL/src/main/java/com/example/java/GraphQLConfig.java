package com.example.java;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Configuration
class GraphQLConfig {

    @Autowired
    private GraphQLDataFetchers graphQLDataFetchers;
    @Autowired
    private Optional<List<RuntimeWiringConfigurator>> runtimeWiringConfigurerList;

    @Bean
    GraphQL graphQL() throws Exception {
        final String sdl = readAllLineToString(ResourceUtils.getURL("classpath:schema.graphqls").toURI());
        final GraphQLSchema schema = buildSchema(sdl);
        return GraphQL.newGraphQL(schema).build();
    }

    private static String readAllLineToString(URI uri) {
        try {
            return Files.lines(Paths.get(uri), StandardCharsets.UTF_8)
                    .collect(Collectors.joining());
        } catch (IOException e) {
            // ignored
            e.printStackTrace();
        }

        return "";
    }

    private GraphQLSchema buildSchema(String sdl) {
        final TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        final RuntimeWiring runtimeWiring = buildWiring();
        final SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        final RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();
        runtimeWiringConfigurerList.ifPresent(list -> list.forEach(it -> it.configure(builder)));
        return builder.build();
    }

    @Bean
    RuntimeWiringConfigurator bookById() {
        return builder -> {
            builder.type(newTypeWiring("Query")
                    .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()));
        };
    }

    @Bean
    RuntimeWiringConfigurator book() {
        return builder -> {
            builder.type(newTypeWiring("Book")
                    .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()));
        };
    }

    @Bean
    RuntimeWiringConfigurator authorById() {
        return builder -> {
            builder.type(newTypeWiring("Query")
                    .dataFetcher("authorById", graphQLDataFetchers.getAuthorByIdDataFetcher()));
        };
    }
}
