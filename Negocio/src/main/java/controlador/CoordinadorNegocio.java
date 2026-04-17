/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Frame;
import dtos.ClienteDTO;
import dtos.ComandaDTO;
import dtos.IngredienteDTO;
import dtos.MesaDTO;
import dtos.ReporteClienteDTO;
import dtos.ReporteComandaDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import objetosnegocio.ClienteBO;
import objetosnegocio.ComandaBO;
import objetosnegocio.IngredienteBO;
import objetosnegocio.MesaBO;
import objetosnegocio.MeseroBO;
import tiposDatosEnums.EstadoIngrediente;
import tiposDatosEnums.UnidadMedida;

/**
 * Clase coordinadora de todos los eventos que requieran de una lógica interna acorde al negocio
 * Se encarga de maanejar las funciones operativas
 * @author aaron
 */
public class CoordinadorNegocio {
    
    private final ClienteBO cliente;
    private final IngredienteBO ingrediente;
    private final MeseroBO mesero;
    private final ComandaBO comanda;
    private final MesaBO mesa;
    //private final ProductoBO producto;
    
    public CoordinadorNegocio() {
        this.cliente = ClienteBO.getInstance();
        this.ingrediente = IngredienteBO.obtenerInstancia();
        this.mesero = MeseroBO.getInstance();
        this.comanda = ComandaBO.getInstance();
        this.mesa = MesaBO.getInstance();
    }
    
    /**
     * Método que decide qué tipo de filtro regresar al usuario
     * @param texto
     * @param tipo
     * @return 
     */
    public List<ClienteDTO> filtraeClientes(String texto, String tipo){
        if(texto == null || texto.isEmpty()){
            return cliente.obtenerTodos();
        }
        return switch(tipo){
            case "Nombre" -> cliente.buscarPorNombre(texto);
            case "Numero" -> cliente.buscarPorTelefono(texto);
            case "Correo" -> cliente.buscarPorCorreo(texto);
            default -> cliente.obtenerTodos();
        };
    }
    
    /**
     * Método que registra clientes en la base de datos
     * @param clienteAgregar
     * @return 
     */
    public ClienteDTO registrarCliente(ClienteDTO clienteAgregar){
        return cliente.registrarCliente(clienteAgregar);
    }
    
    /**
     * Método que regresa un cliente en base al id
     * @param id
     * @return
     * @throws NegocioException 
     */
    public ClienteDTO obtenerCliente(Long id) throws NegocioException{
        return cliente.buscarClienteId(id);
    }
    
    public ClienteDTO modificarClienteFrecuente(ClienteDTO clienteModificar) throws NegocioException{
        return cliente.actualizarCliente(clienteModificar);
    }
    
    // --- Ingrediente ---
    /**
     * Método para filtrar ingredientes 
     * @param texto que se busca filtrar
     * @param tipoFiltro como se va a filtrar
     * @return
     * @throws NegocioException 
     */
    public List<IngredienteDTO> filtrarIngrediente(String texto, String tipoFiltro) throws NegocioException{
        return ingrediente.buscarIngredientes(texto, tipoFiltro);
    }
    
    /**
     * Método que llama al bo para eliminar un ingrediente en concreto
     * @param id
     * @throws NegocioException 
     */
    public void eliminarIngrediente(Long id) throws NegocioException{
        ingrediente.eliminarIngrediente(id);
    }
    /**
     * Método para registrar un ingrediente
     * @param nombre
     * @param cantidad
     * @param unidad
     * @param estado
     * @param url
     * @param imagen
     * @throws NegocioException 
     */
    public void registrarIngrediente(String nombre, Double cantidad, UnidadMedida unidad, EstadoIngrediente estado, String url, byte[] imagen) throws NegocioException{
        IngredienteDTO  ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setNombre(nombre);
        ingredienteDTO.setCantidad_stock(cantidad);
        ingredienteDTO.setUnidad_medida(unidad.name());
        ingredienteDTO.setEstado(estado.name());
        ingredienteDTO.setUrl(url);
        ingredienteDTO.setImagen(imagen);
        System.out.println("url: " + url + "i: " + imagen);
        ingrediente.registrarIngrediente(ingredienteDTO);
    }
    /**
     * Método que obtiene un ingrediente de la base de datos en base a un id
     * @param id
     * @return
     * @throws NegocioException 
     */
    public IngredienteDTO obtenerIngrediente(Long id) throws NegocioException{
        return ingrediente.obtenerIngrediente(id);
    }
    
    /**
     * Método para modificar un ingrediente ya existente con datos nuevos
     * @param id
     * @param nombre
     * @param cantidad
     * @param unidad
     * @param estado
     * @param url
     * @param imagen
     * @return 
     */
    public IngredienteDTO modificarIngrediente(Long id, String nombre, Double cantidad, UnidadMedida unidad, EstadoIngrediente estado, String url, byte[] imagen) throws NegocioException{
        // crear objeto
        IngredienteDTO  ingredienteDTO = new IngredienteDTO();
        // setear valores
        ingredienteDTO.setId(id);
        ingredienteDTO.setNombre(nombre);
        ingredienteDTO.setCantidad_stock(cantidad);
        ingredienteDTO.setUnidad_medida(unidad.name());
        ingredienteDTO.setEstado(estado.name());
        ingredienteDTO.setUrl(url);
        ingredienteDTO.setImagen(imagen);
        return ingrediente.actualizarIngrediente(ingredienteDTO);
    }
    
    /**
     * metodo para iniciar sesion como empleado
     * @param codigo
     * @throws NegocioException
     * @throws PersistenciaException 
     */
    public void iniciarSesionMesero(String codigo) throws NegocioException, PersistenciaException {
        mesero.iniciarSesion(codigo);
    }
    
    
    // --- Reportes ---
    public List<ReporteComandaDTO> generarReporteComandas(LocalDateTime inicio, LocalDateTime fin) throws NegocioException{
        return comanda.generarReporteComanda(inicio, fin);
    }
    
    
    
    public List<ComandaDTO> buscarComandasPorFecha(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException, NegocioException{
           return comanda.buscarPorRangoFechas(inicio, fin);
       
    }
    
    public List<ReporteClienteDTO> generarReporteClientes(String nombre) throws NegocioException{
        return cliente.generarReporteClientes(nombre);
    }
    
    public List<MesaDTO> obtenerMesas() throws PersistenciaException{
        return mesa.obtenerTodas();
    }
    
    //el metodo para llamar a registrarcomanda del comandaBO
    public ComandaDTO registrarComanda(ComandaDTO nuevaComanda) throws NegocioException, PersistenciaException {
        return comanda.registrarComanda(nuevaComanda);
    }
    
    /**
     * genera un folio para las comandas
     * @return
     * @throws PersistenciaException 
     */
    public String darFolio() throws PersistenciaException{
        return comanda.crearFolio();
    }
    
    /**
     * cambia el estado de la mesa a OCUPADA
     * @param idMesa
     * @throws PersistenciaException 
     */
    public void ocuparMesa(Long idMesa) throws PersistenciaException {
        mesa.ocuparMesa(idMesa);
    }
    
    //metodos que meti mientras hago la parte de cerrar comandas
    
    /**
     * metodo para buscar una comanda por su id
     * @param id
     * @return
     * @throws NegocioException 
     */
    public ComandaDTO buscarComandaPorId(Long id) throws NegocioException {
        return comanda.buscarComandaId(id);
    }

    /**
     * metodo para actualizar una comanda
     * @param dto
     * @return
     * @throws PersistenciaException
     * @throws NegocioException 
     */
    public ComandaDTO actualizarComanda(ComandaDTO dto) throws PersistenciaException, NegocioException {
        return comanda.actualizarComanda(dto);
    }

    /**
     * metodo que cambia el estado de una mesa a DISPONIBLE
     * @param idMesa
     * @throws PersistenciaException 
     */
    public void desocuparMesa(Long idMesa) throws PersistenciaException {
        mesa.desocuparMesa(idMesa);
    }
    
    /**
     * metodo para cerrar una comanda
     * @param idComanda
     * @throws NegocioException
     * @throws PersistenciaException 
     */
    public void cerrarComanda(Long idComanda) throws NegocioException, PersistenciaException{
        comanda.cerrarComanda(idComanda);
    }
    
}
