/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio;

import adaptadores.ComandaAdapter;
import adaptadores.ReporteComandaAdapter;
import daos.ClienteDAO;
import daos.ComandaDAO;
import daos.MesaDAO;
import dtos.ClienteDTO;
import dtos.ComandaDTO;
import dtos.ReporteComandaDTO;
import entidades.Cliente;
import entidades.Comanda;
import entidades.DetalleComanda;
import entidades.Mesa;
import entidades.Mesero;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import sesiones.SesionMesero;
import tiposDatosEnums.EstadoComanda;
import tiposDatosEnums.EstadoMesa;

/**
 *
 * @author luiscarlosbeltran
 */
public class ComandaBO {
    //hecho como singleton
    private static ComandaBO instancia;

    private final ComandaDAO comandaDAO;
    private final MesaDAO mesaDAO;
    private final ClienteDAO clienteDAO;
    private Comanda comanda;

    private ComandaBO() {
        this.comandaDAO = new ComandaDAO();
        this.mesaDAO = new MesaDAO();
        this.comanda = new Comanda();
        this.clienteDAO = new ClienteDAO();
    }

    //el constructor publico y static para que sea solo una instancia en todo el sistema
    //osea el singleton
    public static ComandaBO getInstance() {
        if (instancia == null) {
            instancia = new ComandaBO();
        }
        return instancia;
    }
    
    //---empiezan los metodos
    
    /**
     * metodo para guardar una comanda, la recibe como dto y la persiste como entity
     * y cambia el estado de la mesa en la que se guardo
     * @param dto
     * @return
     * @throws NegocioException
     * @throws PersistenciaException 
     */
    public ComandaDTO registrarComanda(ComandaDTO dto) throws NegocioException, PersistenciaException{
        //primero validaciones
        if(dto == null){
            throw new NegocioException("La comanda no puede estar vacia");
        }
        
        if(dto.getDetalles() == null || dto.getDetalles().isEmpty()){
            throw new NegocioException("La comanda debe tener al menos un detalle registrado");
        }
        
        if(!mesaDAO.disponible(dto.getIdMesa())){
            throw new NegocioException("La mesa no esta disponible");
        }
        
        Mesero meseroActual = SesionMesero.getMeseroActual();
        
        if(meseroActual == null){
            throw new NegocioException("No se encontro un mesero registrado");
        }
        
        //convierte dto a entity con el metodo del adapter
        Comanda comanda = ComandaAdapter.DTOAEntity(dto);
        
        //asigna mesero
        comanda.setMesero(meseroActual);
        
        //calcula el total acumulado
        double total = 0.0;
        for (DetalleComanda d : comanda.getDetalles()){
            total += d.getSubtotal();
        }
        comanda.setTotal_acumulado(total);
        
        //guarda la comanda con el metodo del dao
        Comanda guardada = comandaDAO.guardarComanda(comanda);
        
        //cambia el estado de la mesa
        Mesa mesa = mesaDAO.buscarPorId(dto.getIdMesa());
        
        System.out.println("ID mesa: " + mesa.getId());
System.out.println("Identificador: " + mesa.getIdentificador());
System.out.println("Estado actual: " + mesa.getEstado());
        
        mesa.setEstado(EstadoMesa.OCUPADA);
        mesaDAO.actualizar(mesa);
        
        return ComandaAdapter.ComandaADTO(guardada);
    }
    
    /**
     * metodo que regresa una lista de todas las comandas en su forma dto
     * @return
     * @throws PersistenciaException 
     */
    public List<ComandaDTO> obtenerTodas() throws PersistenciaException{
        List<Comanda> comandas = comandaDAO.obtenerTodos();
        return ComandaAdapter.listaComandaAListaDTO(comandas);
    }
    
    /**
     * metodo que regresa todas las comandas creadas entre un rango de fechas, en su forma dto
     * @param inicio
     * @param fin
     * @return
     * @throws PersistenciaException
     * @throws NegocioException 
     */
    public List<ComandaDTO> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException, NegocioException{
        if(inicio.isAfter(fin)){
            throw new NegocioException("La fecha de inicio debe ir antes de fin");
        }
        List<Comanda> comandas = comandaDAO.buscarPorRangoFechas(inicio, fin);
        return ComandaAdapter.listaComandaAListaDTO(comandas);
    }
    
    /**
     * metodo para actualizar una comanda, solo se puede si su estado es abierta
     * @param dto
     * @return
     * @throws PersistenciaException
     * @throws NegocioException 
     */
    public ComandaDTO actualizarComanda(ComandaDTO dto) throws PersistenciaException, NegocioException{
        //intenta buscar la comanda que sera actualizada
        Comanda buscada = comandaDAO.buscarPorId(dto.getId());
        
        //algunas validaciones
        if(buscada == null){
            throw new PersistenciaException("La comanda no existe");
        }
        
        if(buscada.getEstado() != EstadoComanda.ABIERTA){
            throw new NegocioException("Solo se pueden modificar comandas abiertas");
        }
        
        //convierte la comanda de dto a entity
        Comanda actualizada = ComandaAdapter.DTOAEntity(dto);
        
        //vuelve a calcular el total
        double total = 0.0;
        for(DetalleComanda d : actualizada.getDetalles()){
            total += d.getSubtotal();
        }
        actualizada.setTotal_acumulado(total);
        
        Comanda resultado = comandaDAO.actualizarComanda(actualizada);       
        return ComandaAdapter.ComandaADTO(resultado);
    }
    
    public List<ReporteComandaDTO> generarReporteComanda(LocalDateTime inicio, LocalDateTime fin) throws NegocioException{
        try {
            if(inicio == null){
                throw new NegocioException("Fecha inicial no puede ser nula.");
            }
            if(fin == null){
                throw new NegocioException("Fecha final no puede ser nula.");
            }
            // validacion si la inicial es menor a la final
            if(inicio.isAfter(fin)){
                throw new NegocioException("Fecha final no puede ser antes que la inicial.");
            }
            
            return ReporteComandaAdapter.convertirListaEntidadADTO(comandaDAO.obtenerComandasFechas(inicio, fin));
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    
    public String crearFolio() throws PersistenciaException{
        int numFolio = comandaDAO.obtenerNumSigFolio(LocalDateTime.now());
        String folio = comanda.generarFolio(numFolio);
        return folio;
    }
    
    /**
     * metodo que busca una comanda pro su id y la devuelve como dto
     * @param id
     * @return
     * @throws NegocioException 
     */
    public ComandaDTO buscarComandaId(Long id) throws NegocioException {
        try{
            ComandaDTO buscada = ComandaAdapter.ComandaADTO(comandaDAO.buscarPorId(id));
            return buscada;
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    /**
     * metodo para cerrar una comanda, actualiza comanda, cliente y mesa
     * @param idComanda
     * @throws NegocioException
     * @throws PersistenciaException 
     */
    public void cerrarComanda(Long idComanda) throws NegocioException, PersistenciaException {

        if (idComanda == null) {
            throw new NegocioException("ID de comanda inválido");
        }

        //busca comanda
        ComandaDTO comanda = buscarComandaId(idComanda);

        if (comanda == null) {
            throw new NegocioException("La comanda no existe");
        }

        if (!comanda.getEstado().equalsIgnoreCase("ABIERTA")) {
            throw new NegocioException("Solo se pueden cerrar comandas abiertas");
        }

        //cambia el estado
        comanda.setEstado("ENTREGADA");

        actualizarComanda(comanda);


        //para actualizar cliente si tiene uno
        if (comanda.getIdCliente() != null) {

            ClienteDTO cliente = ClienteBO.getInstance().buscarClienteId(comanda.getIdCliente());

            //si la comanda tiene cun cliente, le actualiza puntos y total gastado
            if (cliente != null) {
                
                double gastadoActual =
                    cliente.getTotalGastado() == null
                    ? 0.0
                    : cliente.getTotalGastado();

                double nuevoTotal =
                    gastadoActual + comanda.getTotalAcumulado();

                cliente.setTotalGastado(nuevoTotal);

            
                double puntosActuales =
                    cliente.getPuntos() == null
                    ? 0.0
                    : cliente.getPuntos();

            //1 punto por cada 20gastado
                double puntosGanados =
                    Math.floor(comanda.getTotalAcumulado() / 20.0);

            cliente.setPuntos(
                    puntosActuales + puntosGanados
            );

            ClienteBO.getInstance()
                    .actualizarCliente(cliente);
        }
    }


    //desocupa la mesa
    MesaBO.getInstance().desocuparMesa(comanda.getIdMesa());
}
    
}