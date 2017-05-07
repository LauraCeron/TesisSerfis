<%-- 
    Document   : examen
    Created on : 30/04/2017, 11:04:27
    Author     : Lau Cerón
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="datExamen" method="post">
        <h1>Creación Proceso del Examen Complexivo</h1>

        <h5>Datos</h5>
        <table>
            <tr>
                <td>
                    <label>Tipo Examen</label>
                </td>
                <td>
                    <select name="tipoExamen" style="width:150px;height:25px">
                        <option value="0" selected>(Seleccione)</option>
                        <option value="complexivo">Examen Complexivo</option>
                        <option value="finCarrera">Examen Fin Carrera</option>
                        <option value="mediaCarrera">Examen Media Carrera</option>
                        <!--            <option value="800M Run">800M Run</option>-->
                    </select>
                </td>
            </tr>
            <tr>
                <td>
            <labe>Descripción</labe>
                </td>
                <td>
                    <input name="descripcion" style="width:150px;height:20px">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Numero de Preguntas</label>
                </td>
                <td>
                    <input type="number" name="numPreguntas" min="1" style="width:150px;height:20px"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Fecha de Inicio</label> 
                </td>
                <td>
                    <input type="date" name="fechaInicio" style="width:150px;height:20px"/>
                </td>
            </tr>
            <tr>
                <td>
                     <label>Fecha de Fin</label> 
                </td>
                <td>
                    <input type="date" name="fechaFin" style="width:150px;height:20px" inputmode="yyyy-MM-dd"/>
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
