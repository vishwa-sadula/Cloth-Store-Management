package com.cloth_management.Repository;

import com.cloth_management.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<User>(User.class), username);
    }

    @Override
    public User checkUsernameExists(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                    new BeanPropertyRowMapper<User>(User.class), username);
        }catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public User getUser(int user_id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?",
                new BeanPropertyRowMapper<User>(User.class), user_id);
    }

    @Override
    public int addUser(User user) {

        String password = user.getUser_password();
        password = bCryptPasswordEncoder.encode(password);

        if (user.getUser_type() == null) {
            user.setUser_type("USER");
        }

        if (checkUsernameExists(user.getUsername()) == null) {
            try
            {
           return jdbcTemplate.update("INSERT INTO users (user_fname, user_lname, username, user_email, " +
                            " user_password, city,  street,  pin) VALUES (?,?,?,?,?,?,?,?)",
                    user.getUser_fname(), user.getUser_lname(), user.getUsername(), user.getUser_email(),
                    password, user.getCity(), user.getStreet(), user.getPin());
            } catch (Exception e) {
                return -1;
            }
        }
        else
        {
            return 0;
        }
    }

//    @Override
//    public int updateUser(User user) {
//        String password = bCryptPasswordEncoder.encode(user.getUser_password());
//
//        try{
//        return jdbcTemplate.update("UPDATE users SET user_fname=?, user_lname=?, user_email=?, city=?,  street=?,  pin=? WHERE username=? ", user.getUser_fname(), user.getUser_lname(),
//                         user.getUser_email(),user.getCity(),user.getStreet(),user.getPin(),user.getUsername());
//        } catch (Exception e) {
//            return 0;
//        }
//
//    }

   // @Override
//    public List<String> getPhones(int user_id)
//    {
//       return jdbcTemplate.query("SELECT user_phoneno FROM users_phone_no WHERE user_id = ?",
//               new BeanPropertyRowMapper<String>(String.class),user_id);
////            @Override
////            public UserPhoneNo mapRow(ResultSet rs, int rowNum) throws SQLException {
////                return new UserPhoneNo(rs.getString("user_phoneno"), rs.getInt("user_id"));
////            }
////        },user_id);
//    }
//
//    @Override
//    public int addPhone(UserPhoneNo userPhoneNo) {
//        //try{
//        return  jdbcTemplate.update("INSERT INTO user_phone_no(user_phoneno,user_id) VALUES (?,?)",
//                userPhoneNo.getUser_phoneno(), userPhoneNo.getUser_id());
//
////        } catch (Exception e) {
////            return 0;
////        }
//    }

}
