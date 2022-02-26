package com.amazon.test_data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class Footer {

    public static Stream<Arguments> footerColumns() {
        return Stream.of(
                Arguments.of("Get to Know Us",
                        List.of("Careers", "Blog", "About Amazon", "Investor Relations", "Amazon Devices", "Amazon Science")),
                Arguments.of("Amazon Payment Products",
                        List.of("Amazon Business Card", "Shop with Points", "Reload Your Balance", "Amazon Currency Converter"))
        );
    }
}