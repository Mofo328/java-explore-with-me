package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.group.Marker;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatIntoDto {

    @NotBlank(groups = {Marker.OnCreate.class})
    @Size(max = 255)
    String app;

    @NotBlank(groups = {Marker.OnCreate.class})
    @Size(max = 255)
    String uri;

    @NotBlank(groups = {Marker.OnCreate.class})
    @Size(max = 255)
    String ip;

    @NotNull(groups = {Marker.OnCreate.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime timestamp;
}
