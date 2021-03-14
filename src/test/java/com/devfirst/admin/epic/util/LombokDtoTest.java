package com.devfirst.admin.epic.util;

import com.devfirst.admin.epic.dto.LombokDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LombokDtoTest {

    @Test
    public void 롬복테스트() throws Exception {
        LombokDto lombokDto = new LombokDto("name", 30);
        assertThat(lombokDto.getName()).isEqualTo("name");
        assertThat(lombokDto.getAmount()).isEqualTo(30);
    }
}
