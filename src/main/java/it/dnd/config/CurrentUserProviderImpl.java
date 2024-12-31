package it.dnd.config;
import io.ebean.config.CurrentUserProvider;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CurrentUserProviderImpl implements CurrentUserProvider {

//    @Inject
//    JsonWebToken jwt;

    @Override
    public Object currentUser() {
        return getCurrentUser();
    }

    public String getCurrentUser() {
        //TODO logica reale quando avremo l'utenza in sessione
//        if (jwt != null && StringUtils.isNotBlank(jwt.getSubject()))
//            return jwt.getSubject();
        return "system01";
    }
}
