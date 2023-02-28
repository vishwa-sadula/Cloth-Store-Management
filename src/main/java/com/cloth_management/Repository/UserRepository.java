package com.cloth_management.Repository;

import com.cloth_management.Models.User;

public interface UserRepository {
    User getUserByUsername(String username);

    public int addUser(User user) ;
    public User getUser(int user_id);

//    public int updateUser(User user) ;

   // public List<String> getPhones(int user_id) ;

    public User checkUsernameExists(String username);

   // public int addPhone(UserPhoneNo userPhoneNo) ;


}
