package org.example;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Класс для выполнения операций с базой данных для сущности Course.
 */
public class CourseDAO {

    /**
     * Вставка курса в базу данных.
     *
     * @param course Курс для вставки.
     */
    public void insertCourse(Course course) {
        Cache HibernateUtil = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Получение курса из базы данных по идентификатору.
     *
     * @param courseId Идентификатор курса.
     * @return Курс с указанным идентификатором или null, если курс не найден.
     */
    public Course getCourse(int courseId) {
        Cache HibernateUtil = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = null;
        try {
            course = session.get(Course.class, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return course;
    }

    /**
     * Обновление информации о курсе в базе данных.
     *
     * @param course Курс для обновления.
     */
    public void updateCourse(Course course) {
        Cache HibernateUtil = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Удаление курса из базы данных.
     *
     * @param course Курс для удаления.
     */
    public void deleteCourse(Course course) {
        Cache HibernateUtil = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}