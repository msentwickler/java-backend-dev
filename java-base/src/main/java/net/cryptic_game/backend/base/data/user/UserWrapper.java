package net.cryptic_game.backend.base.data.user;

import net.cryptic_game.backend.base.AppBootstrap;
import net.cryptic_game.backend.base.sql.SQLConnection;
import net.cryptic_game.backend.base.utils.SQLUtils;
import net.cryptic_game.backend.base.utils.SecurityUtils;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserWrapper {

    private static final SQLConnection sqlConnection;

    static {
        final AppBootstrap app = AppBootstrap.getInstance();
        sqlConnection = app.getSqlConnection();
    }

    public static User register(final String name, final String mail, final String password) {
        final LocalDateTime now = LocalDateTime.now();

        final User user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setCreated(now);
        user.setLast(now);

        setPassword(user, password);

        final Session session = sqlConnection.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public static User getById(final UUID id) {
        final Session session = sqlConnection.openSession();
        final User user = session.find(User.class, id);
        session.close();
        return user;
    }

    public static User getByName(final String name) {
        final Session session = sqlConnection.openSession();
        final List<User> users = SQLUtils.selectWhere(session, User.class, "name", name);
        session.close();

        if (!users.isEmpty()) return users.get(0);
        return null;
    }

    public static void setPassword(final User user, final String newPassword) {
        user.setPasswordHash(SecurityUtils.hash(newPassword));
    }

    public static boolean verifyPassword(final User user, final String input) {
        return SecurityUtils.verify(input, user.getPasswordHash());
    }

    public static void setLastToCurrentTime(final User user) {
        final Session session = sqlConnection.openSession();
        session.beginTransaction();
        user.setLast(LocalDateTime.now());
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
}
