package com.wildcodeschool.wildandwizard.repository;

import com.wildcodeschool.wildandwizard.entity.School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private final static String DB_USER = "h4rryp0tt3r";
    private final static String DB_PASSWORD = "Horcrux4life!";

    public List<School> findAll() {

        // TODO : find all schools
            List<School> schools = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM school");
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    School school = new School();
                    school.setId(resultSet.getLong("id"));
                    school.setName(resultSet.getString("name"));
                    school.setCountry(resultSet.getString("country"));
                    schools.add(school);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return schools;
        }

    public School findById(Long id) {

        // TODO : find a school by id
        School school = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM school WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    school = new School();
                    school.setId(resultSet.getLong("id"));
                    school.setName(resultSet.getString("name"));
                    school.setCountry(resultSet.getString("country"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return school;
    }

    public List<School> findByCountry(String country) {

        // TODO : search schools by country
        List<School> schools = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM school WHERE country = ?")) {
            preparedStatement.setString(1, country);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    School school = new School();
                    school.setId(resultSet.getLong("id"));
                    school.setName(resultSet.getString("name"));
                    school.setCountry(resultSet.getString("country"));
                    schools.add(school);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }
}
