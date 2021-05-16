package com.github.server.repository;

import com.github.server.entity.User;
import com.github.server.payload.Role;
import com.github.server.repositories.IRepository;
import com.github.server.repositories.Repository;
import com.github.server.utils.HibernateUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepositoryTest {

    private static final IRepository<User> repository = new Repository<>(User.class);

    private static final List<User> mockData = new ArrayList<>();

    @BeforeClass
    public static void setUp() {

        mockData.add(new User(null, "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "secondUserLogin", "secondUserEmail", "secondUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "thirdUserLogin", "thirdUserEmail", "thirdUserPassword", Role.valueOf("USER")));
        mockData.add(new User(null, "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN")));
        mockData.add(new User(null, "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", Role.valueOf("ADMIN")));

        for (User user : mockData) {
            repository.save(user, HibernateUtils.getSession());
        }
    }

    @AfterClass
    public static void tearDown() {
        Collection<User> users = repository.findAll(HibernateUtils.getSession());
        for (User user : users) {
            repository.delete(user, HibernateUtils.getSession());
        }
    }

    @Test
    public void findByLoginFirstUser() {
        User act = repository.findBy("login", "firstUserLogin", HibernateUtils.getSession());
        User exp = new User(act.getId(), "firstUserLogin", "firstUserEmail", "firstUserPassword", Role.valueOf("USER"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByEmailThirdUser() {
        User act = repository.findBy("email", "thirdUserEmail", HibernateUtils.getSession());
        User exp = new User(act.getId(), "thirdUserLogin", "thirdUserEmail", "thirdUserPassword", Role.valueOf("USER"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByLoginFirstAdmin() {
        User act = repository.findBy("login", "firstAdminLogin", HibernateUtils.getSession());
        User exp = new User(act.getId(), "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN"));
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findByEmailSecondAdmin() {
        User act = repository.findBy("email", "secondAdminEmail", HibernateUtils.getSession());
        User exp = new User(act.getId(), "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", act.getRole());
        Assert.assertEquals(exp, act);
    }

    @Test
    public void update() {
        User userToUpdate = repository.findBy("login", "firstUserLogin", HibernateUtils.getSession());
        User exp = new User(
                userToUpdate.getId(),
                "newFirstUserLogin",
                "newFirstUserEmail",
                "newFirstUserPassword",
                Role.valueOf("ADMIN")
        );
        repository.update(exp, HibernateUtils.getSession());
        User act = repository.findBy("id", exp.getId(), HibernateUtils.getSession());
        Assert.assertEquals(exp, act);
        repository.update(userToUpdate, HibernateUtils.getSession());
    }

    @Test
    public void delete() {
        Collection<User> exp = repository.findAll(HibernateUtils.getSession());
        exp.removeIf(u -> u.getEmail().equals("secondUserEmail"));
        User userToDelete = repository.findBy("email", "secondUserEmail", HibernateUtils.getSession());
        repository.delete(userToDelete, HibernateUtils.getSession());
        Collection<User> act = repository.findAll(HibernateUtils.getSession());
        Assert.assertEquals(exp, act);
    }

    @Test
    public void findAllAdmins() {
        Collection<User> exp = new ArrayList<>();
        Collection<User> act = repository.findAllBy("role", Role.valueOf("ADMIN"), HibernateUtils.getSession());
        exp.add(new User(null, "firstAdminLogin", "firstAdminEmail", "firstAdminPassword", Role.valueOf("ADMIN")));
        exp.add(new User(null, "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", Role.valueOf("ADMIN")));
    }

    @Test
    public void findNotExisting(){
        System.out.println(repository.findBy("email", "notExisting", HibernateUtils.getSession()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveSame(){
        repository.save(
                new User(null, "secondAdminLogin", "secondAdminEmail", "secondAdminPassword", Role.valueOf("ADMIN")),
                HibernateUtils.getSession()
        );
    }



}
