package blog.jdev.forum.hub.api.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Forum HUB API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Janssen Batista",
                        email = "batistajanssen.dev@gmail.com",
                        url = "https://github.com/janssenbatista"
                )
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenAPIConfiguration {
}
