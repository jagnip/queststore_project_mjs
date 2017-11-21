package com.codecool_mjs.controller.webAccessController.adminActionsController;

import com.codecool_mjs.dataaccess.dao.DaoException;
import com.codecool_mjs.model.Admin;
import com.codecool_mjs.view.webView.TemplatesProcessor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminHomeController implements HttpHandler{

    private TemplatesProcessor templatesProcessorr;
    private Admin loggedUser;

    public AdminHomeController(){
        this.templatesProcessorr = new TemplatesProcessor();
    }

    public void setLoggedUser(Admin loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String responseBody;
        int responseCode;

        try {
            responseBody = showAdminHomePage();
            responseCode = 200;
        } catch (DaoException e) {
            responseBody = "No such page";
            responseCode = 404;
        }

        httpExchange.sendResponseHeaders(responseCode, responseBody.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(responseBody.getBytes());
        os.close();

    }

    public String showAdminHomePage() throws DaoException {
        //temporary example of logged user. To remove when sessions will be implemented
        setLoggedUser(new Admin(15,"Janusz", "Kowal", "j.k@cc.pl", "typoweHasło"));

        Map<String, Object> variables = new HashMap<>();

        variables.put("user", loggedUser);

        templatesProcessorr.setVariables(variables);

        String page = templatesProcessorr.ProcessTemplateToPage("admin-home");
        return page;
    }
}