package com.webservice.movie.week09_jpa_proj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record MovieRequest (
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 100) String director,
        @NotBlank @Size(max = 50) String genre,
        @NotNull @PositiveOrZero Integer rating,
        @NotNull Boolean watched
) { }

