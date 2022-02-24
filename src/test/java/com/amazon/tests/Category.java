package com.amazon.tests;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class Category {

    public static Stream<Arguments> productCategories() {
        return Stream.of(
                Arguments.of("#FoundItOnAmazon",
                        List.of("Women's fashion", "Home decor", "Entertaining", "Organization", "Running", "Indoor plants", "Streetwear", "See more", "Saved"))
        );
    }
}