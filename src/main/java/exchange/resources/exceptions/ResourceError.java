package exchange.resources.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}

