package APIDocNew.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;

@Data
@Scope("prototype")
public class Keys {
    private String Token;
    private String PublicKey;
}
