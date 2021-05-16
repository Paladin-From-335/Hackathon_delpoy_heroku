package com.github.server.handlers;

import com.github.server.controllers.IAdminController;
import com.github.server.controllers.IUserController;
import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.exceptions.BadRequest;
import com.github.server.exceptions.ForbiddenException;
import com.github.server.exceptions.NotFound;
import com.github.server.utils.JsonHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHandler extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(HttpHandler.class);

    private final IUserController userController;

    private final IAdminController adminController;

    public HttpHandler(IUserController userController, IAdminController adminController) {
        this.userController = userController;
        this.adminController = adminController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (BadRequest e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid body");
        } catch (NotFound e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND 404");
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setStatus(204);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
        } else {
            String url = req.getRequestURI();
            try {
                switch (url) {
                    case "/auth":
                        UserAuthDto authDto = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                        if(authDto == null){
                            throw new BadRequest();
                        }
                        String result = Optional.of(this.userController.authorize(authDto)).orElseThrow(BadRequest::new);
                        resp.setContentType("application/json");
                        resp.setStatus(HttpServletResponse.SC_OK);
                        ServletOutputStream out = resp.getOutputStream();
                        out.write(result.getBytes());
                        out.flush();
                        out.close();
                        break;
                    case "/reg":
                        UserRegDto regDto = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                        if(regDto == null){
                            throw new BadRequest();
                        }
                        this.userController.register(regDto);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    default:
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (BadRequest e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }catch (ForbiddenException e) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }catch (ConstraintViolationException e) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            } catch (Throwable e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
