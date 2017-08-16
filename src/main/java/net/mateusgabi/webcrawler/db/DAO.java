package net.mateusgabi.webcrawler.db;

import java.util.Enumeration;

/**
 * @author Mateus Gabi Moreira
 * @email  mateusgabimoreira@gmail.comSS
 */
public interface DAO<T> {

    public abstract DAO.Response adicionar(T t);

    public static class Response {

        public static enum Status { OK, ERROR }

        private String message;
        private Status status;

        public String getMessage() {
            return message;
        }

        public Response setMessage(String message) {
            this.message = message;

            return this;
        }

        public Status getStatus() {
            return status;
        }

        public Response setStatus(Status status) {
            this.status = status;

            return this;
        }
    }

}
