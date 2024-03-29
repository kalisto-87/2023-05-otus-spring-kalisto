package ru.otus.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "genre")
public class MongoGenre {

    @Id
    private String id;

    private String name;

    public MongoGenre(String name) {
        this.name = name;
    }
}
