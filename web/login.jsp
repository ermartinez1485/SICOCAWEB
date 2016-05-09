<%-- 
    Document   : LOGIN
    Created on : Apr 26, 2016, 6:29:30 PM
    Author     : eric.martinez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>

<f:view>
<html> 

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SICOCA Login Form</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>
        <f:loadBundle basename="cr.ac.una.icai.sicocaweb.etiquetas.recursos" var="eti"/>
        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>SICOCA</strong> Sistema de Control de Capacitaciones</h1>
                            <div class="description">
                            	<p>
	                            	
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Login to our site</h3>
                            		<p>Ingrese su usuario y password:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
                                
                                <h:form id="form1" styleClass="login-form">
                                    <div class="form-group">
                                        <label class="sr-only" for="form-username"><h:outputText value="#{eti['tabla.user']}"/></label>
                                        <h:inputText
                                        
                                        styleClass="form-username form-control"
                                        id="txtUser" 
                                        required="true" 
                                        requiredMessage="es requerido"
                                        value="#{usuarioBean.elUser.nombreUsuario}" />
                                        <h:message for="txtUser"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-password"><h:outputText value="#{eti['tabla.password']}"/></label>
                                        <h:inputSecret 
                                            id="txtPass" 
                                            styleClass="form-password form-control"
                                            required="true" 
                                            requiredMessage="es requerida"
                                            value="#{usuarioBean.elUser.password}" />
                                    </div>
                                        
			                    <h:commandButton 
                                                value="#{eti['boton.Login']}" 
                                                id="btnAceptar"
                                                styleClass="btn btn-primary btn-block"
                                                action="#{usuarioBean.aceptar()}"/>
                                            <h:messages globalOnly="true"/>
                                </h:form>    
		            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
    </body>
</html>
</f:view>
