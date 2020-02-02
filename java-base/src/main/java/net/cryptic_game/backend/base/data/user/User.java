package net.cryptic_game.backend.base.data.user;

import com.google.gson.JsonObject;
import net.cryptic_game.backend.base.sql.models.TableModelAutoId;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class User extends TableModelAutoId {

    @Column(name = "name", updatable = true, nullable = false)
    private String name;

    @Column(name = "mail", updatable = true, nullable = false)
    private String mail;

    @Column(name = "password", updatable = true, nullable = false)
    private String passwordHash;

    @Column(name = "created", updatable = false, nullable = false)
    private LocalDateTime created;

    @Column(name = "last", updatable = true, nullable = false)
    private LocalDateTime last;

    @Override
    public JsonObject serialize() {
        final JsonObject json = new JsonObject();
        json.addProperty("id", this.getId().toString());
        json.addProperty("name", this.getName());
        json.addProperty("mail", this.getMail());
        json.addProperty("created", this.getCreated().toInstant(ZoneOffset.UTC).toEpochMilli());
        json.addProperty("last", this.getLast().toInstant(ZoneOffset.UTC).toEpochMilli());
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(final String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLast() {
        return this.last;
    }

    public void setLast(final LocalDateTime last) {
        this.last = last;
    }
}