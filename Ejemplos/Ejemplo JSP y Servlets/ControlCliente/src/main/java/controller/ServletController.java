package controller;

import datos.ClienteDaoMySQL;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Con esta notcion escribis el nombre con el que se llamara al Servlet
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {

    //metodo para la forma de envio doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            this.accionDefault(request, response);
        }
        if (accion != null) {
            switch (accion) {
                case "updateCliente":
                    this.editarCliente(request, response);
                    break;
                case "delete":
                    this.eliminarCliente(request, response);
                    this.accionDefault(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
                    break;
            }
        }
    }

    //metodo para la forma de envio doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            this.accionDefault(request, response);
        }
        if (accion != null) {
            switch (accion) {
                case "insert":
                    this.agregarCliente(request, response);
                    this.accionDefault(request, response);
                    break;
                case "update":
                    this.actualizarCliente(request, response);
                    this.accionDefault(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
                    break;
            }
        }
    }

    /**
     * Recupera todos los clientes de la BD
     *
     * @return
     */
    private List<Cliente> recuperarClientesBD() {
        List<Cliente> clientes = new ClienteDaoMySQL().selectAll();
        return clientes;
    }

    /**
     * Es la accion que se ejecuta si no se hace nada
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = this.recuperarClientesBD();
        //Estos atributos se envian al JSP en el alcance de session
        HttpSession session = request.getSession();
        session.setAttribute("clientes", clientes);
        //este es un Mapa de datos, este tipo de lista la encontras en la API collencion
        session.setAttribute("totalClientes", clientes.size());
        session.setAttribute("saldoTotal", calcularSaldoTotal(clientes));

        //Este metodo redirige la pagina a la ruta especificada con los parametros de request y response
        //request.getRequestDispatcher("view/clientes.jsp").forward(request, response); 
        response.sendRedirect("view/clientes.jsp");
    }

    /**
     * Recupera los datos del cliente del formulario
     *
     * @param request
     * @return
     */
    private Cliente nuevoCliente(HttpServletRequest request) {
        String saldoString = request.getParameter("saldo");
        double saldo = 0;
        if (saldoString != null && !saldoString.equals("")) {
            saldo = Double.parseDouble(saldoString);
        }
        Cliente cliente = new Cliente(
                request.getParameter("nombre"),
                request.getParameter("apellido"),
                request.getParameter("email"),
                request.getParameter("telefono"),
                saldo);
        return cliente;
    }

    /**
     * Agrega un cliente a la BD
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void agregarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = nuevoCliente(request);
        int registosModificado = new ClienteDaoMySQL().insert(cliente);
    }

    /**
     * Redirige a la pagina de Edicion segun el registo que presionaste
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDaoMySQL().selectById(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);

        String jsp = "/WEB-INF/pages/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jsp).forward(request, response);
    }
    
    /**
     * Actualiza la BD con los nuevos datos
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String saldoString = request.getParameter("saldo");
        double saldo = 0;
        if (saldoString != null && !saldoString.equals("")) {
            saldo = Double.parseDouble(saldoString);
        }
        Cliente cliente = new Cliente(
                Integer.parseInt(request.getParameter("idCliente")),
                request.getParameter("nombre"),
                request.getParameter("apellido"),
                request.getParameter("email"),
                request.getParameter("telefono"),
                saldo
        );
        
        int registrosModificados = new ClienteDaoMySQL().update(cliente);
    }
    
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int registrosModificados = new ClienteDaoMySQL().delete(new Cliente(idCliente));  
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}
