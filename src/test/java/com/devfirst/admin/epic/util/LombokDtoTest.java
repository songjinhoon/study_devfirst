package com.devfirst.admin.epic.util;

import com.devfirst.admin.epic.dto.LombokDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LombokDtoTest {

    @Test
    @DisplayName("Lombok test")
    public void test01() throws Exception {
        LombokDto lombokDto = new LombokDto("name", 30);
        assertThat(lombokDto.getName()).isEqualTo("name");
        assertThat(lombokDto.getAmount()).isEqualTo(30);
    }
}