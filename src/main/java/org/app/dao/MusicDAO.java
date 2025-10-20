package org.app.dao;

import org.app.model.Music;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // Marks this as a class that handles the Database related work, also helps in exception translation.
public class MusicDAO {

    private final JdbcTemplate jdbcTemplate;

    public MusicDAO(JdbcTemplate jdbcTemplate){ //Constructor DI used
        this.jdbcTemplate = jdbcTemplate;
    }


    // Now we'll add methods to do the data management.

    // Adding a music file
    public int addMusic(Music music){ // Using int return type for basically the confirmation of whether operation succeeded or not.
        return jdbcTemplate.update("INSERT INTO music (name, type) VALUES (?, ?)", music.getName(), music.getType());
    }

    // Fetching all music
    public List<Music> findAll(){
        return jdbcTemplate.query("Select * from music ", new MusicRowMapper());
    }

    // Updating the music
    public int updateMusic(String newType, String name){
        return jdbcTemplate.update("UPDATE music SET music_file_type=? WHERE music_name=?", newType, name);
    }

    // Delete music
    public int deleteMusic(String name){
        return jdbcTemplate.update("DELETE FROM music WHERE music_name=?", name);
    }

    // RowMapper is a Spring Interface that tells Spring how to Map each row of database to an object of type <T> (i.e., Music here)
    private static class MusicRowMapper implements RowMapper<Music>{
        @Override
        public Music mapRow(ResultSet resultSet, int row) throws SQLException { // Abstract method in RowMapper Interface that must be implemented.
                Music music = new Music();
                music.setName(resultSet.getString("name"));
                music.setType(resultSet.getString("type"));
                return music;
            }


    }
}
