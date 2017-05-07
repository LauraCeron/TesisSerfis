<%-- 
    Document   : asignacion
    Created on : 30/04/2017, 22:15:04
    Author     : Lau Cerón
--%>

<%@page import="MODEL.clsUsuario"%>
<%@page import="BEAN.BeanUsuario"%>
<%@page import="MODEL.clsMateria"%>
<%@page import="java.util.List"%>
<%@page import="BEAN.BeanMateria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="datAsignacion" method="post">
        <h1>ASIGNACION</h1>
        
        <table>
            <tr>
                <td>
                <label>Materia</label>
            </td>
                <td>
                     <select name="cmbMateria" style="width:150px;height:25px">
                        <option value="0" selected>(Seleccione)</option>
                        <%
                            BeanMateria beanMateria = new BeanMateria();
                            List<clsMateria> listaMateria= beanMateria.listarMateria() ;
                            for(clsMateria materia: listaMateria)
                            { %>
                            <option value="<%=materia.getCodMateria()%>"><%=materia.getNombreMateria()%></option> 
                         <%   } %>
                       
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                <label>Docente</label>
            </td>
                <td>
                     <select name="cmbUsuario" style="width:150px;height:25px">
                        <option value="0" selected>(Seleccione)</option>
                        <%
                            BeanUsuario beanUsuario = new BeanUsuario();
                            List<clsUsuario> listaUsuario= beanUsuario.listarUsuario();
                            for(clsUsuario usuario: listaUsuario)
                            { %>
                            <option value="<%=usuario.getCodUsuario()%>"><%=usuario.getNombreUsuario()%></option> 
                         <%   } %>
                       
                    </select>
                </td>
            </tr>
            <td>
                <label>Numero de Preguntas</label>
            </td>
                <td>
                    <input type="number" name="numPreguntas" min="1" style="width:150px;height:20px"/>
                </td>
            <tr>
                <td>
                     <label>Bibliografia Sugerida</label>
                </td>
                <td>
                     <input name="biblioSugerida" style="width:150px;height:20px"/>
                </td>
            </tr>
           <tr>
                <td>
                    <input type='submit' name='btnGuardar' value="Guardar"/>
                </td>
            </tr>
        </table>
        </form>
    </body>
</html>
