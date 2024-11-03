/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iudigital.project.view;
import com.iudigital.project.controller.EstadoCivilController;
import com.iudigital.project.controller.FuncionarioController;
import com.iudigital.project.controller.GrupoFamiliarController;
import com.iudigital.project.controller.InformacionAcademicaController;
import com.iudigital.project.controller.NivelEstudioController;
import com.iudigital.project.controller.SexoController;
import com.iudigital.project.controller.TipoIdentificacionController;
import com.iudigital.project.controller.UniversidadController;
import com.iudigital.project.domain.Funcionario;
import com.iudigital.project.domain.EstadoCivil;
import com.iudigital.project.domain.GrupoFamiliar;
import com.iudigital.project.domain.InformacionAcademica;
import com.iudigital.project.domain.NivelEstudio;
import com.iudigital.project.domain.Sexo;
import com.iudigital.project.domain.TipoIdentificacion;
import com.iudigital.project.domain.Universidad;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 *
 * @author jdguerra
 */
public class NewJFrame extends javax.swing.JFrame {

    
    private FuncionarioController funcionarioController = null;
    private EstadoCivilController estadoCivilController = null;
    private GrupoFamiliarController grupoFamiliarController = null;
    private InformacionAcademicaController informacionAcademicaController = null;
    private NivelEstudioController nivelEstudioController = null;
    private SexoController sexoController = null;
    private TipoIdentificacionController tipoIdentificacionController = null;
    private UniversidadController universidadController = null;
    private static final String[] FUNCIONARIO_COLUMNS = {
        "ID", "NOMBRE", "APELLIDO", "T.I", 
        "N. IDENTIFICACION", "GENERO", "ESTADO CIVIL", "F. NACIMIENTO", "TELEFONO", 
        "DIRECCION", "INFORMACION ACADEMICA", "GRUPO FAMILIAR", "entrenador "
    };
    private static final String TIPOSIDENTIFICACION = "-Tipo Identificación-";
    private static final String GENEROS = "-Genero-";
    private static final String ESTADOSCIVILES = "-Estado Civil-";
    private static final String TIPOUNIVERSIDAD = "-Tipo Universidad-";
    private static final String TITULOSUNIVERSITARIOS = "-Titulo Universitario-";
    private static final String NIVELESESTUDIO = "-Nivel de estudio-";
    private static final String PARENTESCOS = "-Parentesco-";
    private static final String SELECCIONE = "-SELECCIONE-";
    
    
    public NewJFrame() {
        initComponents();
        
        funcionarioController = new FuncionarioController();
        estadoCivilController = new EstadoCivilController();
        grupoFamiliarController = new GrupoFamiliarController();
        informacionAcademicaController = new InformacionAcademicaController();
        nivelEstudioController = new NivelEstudioController();
        sexoController = new SexoController();
        tipoIdentificacionController = new TipoIdentificacionController();
        universidadController = new UniversidadController();
        txtFuncionarioId.setEnabled(false);
        listFuncionarios();
        listTiposIdentificacion();
        listSexos();
        listEstadosCiviles();
        listUniversidades();
        listNivelesEstudio();
        addListeners();
        
        
        
    }
    
    private void listFuncionarios() {
        cbxFuncionarios.removeAllItems();
        Funcionario funcionarioUno = new Funcionario();
        funcionarioUno.setNombre(SELECCIONE);
        funcionarioUno.setApellido("");
        funcionarioUno.setNumeroIdentificacion("");
        cbxFuncionarios.addItem(funcionarioUno);
        tblFuncionarios.removeAll();
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String COLUMN : FUNCIONARIO_COLUMNS) {
            defaultTableModel.addColumn(COLUMN);
        }

        tblFuncionarios.setModel(defaultTableModel);
        
        try {
            List<Funcionario> funcionarios = funcionarioController.getFuncionarios();
            if (funcionarios.isEmpty()) {
                return;
            }
            defaultTableModel.setRowCount(funcionarios.size());
            int row = 0;
            for (Funcionario funcionario : funcionarios) {
                defaultTableModel.setValueAt(funcionario.getIdFuncionario(), row, 0);
                defaultTableModel.setValueAt(funcionario.getNombre(), row, 1);
                defaultTableModel.setValueAt(funcionario.getApellido(), row, 2);
                defaultTableModel.setValueAt(funcionario.getTipoIdentificacion().getTipo(), row, 3);
                defaultTableModel.setValueAt(funcionario.getNumeroIdentificacion(), row, 4);
                defaultTableModel.setValueAt(funcionario.getSexo().getSexo(), row, 5);
                defaultTableModel.setValueAt(funcionario.getEstadoCivil().getEstadoCivil(), row, 6);
                defaultTableModel.setValueAt(funcionario.getFechaNacimiento(), row, 7);
                defaultTableModel.setValueAt(funcionario.getTelefono(), row, 8);
                defaultTableModel.setValueAt(funcionario.getDireccion(), row, 9);
                defaultTableModel.setValueAt(funcionario.getInformacionAcademica(), row, 10);
                defaultTableModel.setValueAt(funcionario.getGruposFamiliares(), row, 11);
                row++;
                

                cbxFuncionarios.addItem(funcionario);
//                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void listTiposIdentificacion() {
        cbxTipoIdentificacionEdit.removeAllItems();
        cbxTipoIdentificacion.removeAllItems();
        TipoIdentificacion tipoidentificacionuno = new TipoIdentificacion();
        tipoidentificacionuno.setTipo(TIPOSIDENTIFICACION);
        cbxTipoIdentificacionEdit.addItem(tipoidentificacionuno);
        cbxTipoIdentificacion.addItem(tipoidentificacionuno);
        try {
            List<TipoIdentificacion> tiposidentificacion = tipoIdentificacionController.getTiposIdentificacion();
            for (TipoIdentificacion tipoidentificacion : tiposidentificacion) {
                cbxTipoIdentificacionEdit.addItem(tipoidentificacion);
                cbxTipoIdentificacion.addItem(tipoidentificacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void listSexos() {
        cbxSexoEdit.removeAllItems();
        cbxSexo.removeAllItems();
        Sexo sexouno = new Sexo();
        sexouno.setSexo(GENEROS);
        cbxSexoEdit.addItem(sexouno);
        cbxSexo.addItem(sexouno);
        try {
            List<Sexo> sexos = sexoController.getSexos();
            for (Sexo sexo : sexos) {
                cbxSexoEdit.addItem(sexo);
                cbxSexo.addItem(sexo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void listEstadosCiviles() {
        cbxEstadoCivilEdit.removeAllItems();
        cbxEstadoCivil.removeAllItems();
        EstadoCivil estadociviluno = new EstadoCivil();
        estadociviluno.setEstadoCivil(ESTADOSCIVILES);
        cbxEstadoCivilEdit.addItem(estadociviluno);
        cbxEstadoCivil.addItem(estadociviluno);
        try {
            List<EstadoCivil> estadosciviles = estadoCivilController.getEstadosCiviles();
            for (EstadoCivil estadocivil : estadosciviles) {
                cbxEstadoCivilEdit.addItem(estadocivil);
                cbxEstadoCivil.addItem(estadocivil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void listUniversidades() {
    cbxTipoEdit.removeAllItems();
    cbxTipo.removeAllItems();
    Universidad universidadUno = new Universidad();
    universidadUno.setTipo(TIPOUNIVERSIDAD);
    cbxTipoEdit.addItem(universidadUno);
    cbxTipo.addItem(universidadUno);
    try {
        List<Universidad> universidades = universidadController.getUniversidades();
        for (Universidad universidad : universidades) {
            cbxTipoEdit.addItem(universidad);
            cbxTipo.addItem(universidad);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    private void listNivelesEstudio() {
    cbxNivelEstudioEdit.removeAllItems();
    cbxNivelEstudio.removeAllItems();
    NivelEstudio nivelestudiouno = new NivelEstudio();
    nivelestudiouno.setNivelEstudio(NIVELESESTUDIO);
    cbxNivelEstudioEdit.addItem(nivelestudiouno);
    cbxNivelEstudio.addItem(nivelestudiouno);
    try {
        List<NivelEstudio> nivelesestudio = nivelEstudioController.getNivelEstudio();
        for (NivelEstudio nivelestudio : nivelesestudio) {
            cbxNivelEstudioEdit.addItem(nivelestudio);
            cbxNivelEstudio.addItem(nivelestudio);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    
    
    
    
    
    private void addListeners() {
    cbxFuncionarios.addItemListener(event -> {
        Funcionario selectedFuncionario = (Funcionario) event.getItem();
        if (selectedFuncionario.getNombre().equals(SELECCIONE)) {
            
            
            txtNombreEdit.setText("");
            txtApellidoEdit.setText("");
            cbxTipoIdentificacionEdit.setSelectedItem("");
            txtNumeroIdentificacionEdit.setText("");
            cbxSexoEdit.setSelectedItem("");
            cbxEstadoCivilEdit.setSelectedItem("");
            txtFechaNacimientoEdit.setText("");
            txtTelefonoEdit.setText("");
            txtDireccionEdit.setText("");
           
            cbxTipoEdit.setSelectedItem("");
            
        } else {
            
            txtFuncionarioId.setText(String.valueOf(selectedFuncionario.getIdFuncionario()));
            txtNombreEdit.setText(selectedFuncionario.getNombre());
            txtApellidoEdit.setText(selectedFuncionario.getApellido());
            cbxTipoIdentificacionEdit.setSelectedItem(selectedFuncionario.getTipoIdentificacion());
            txtNumeroIdentificacionEdit.setText(selectedFuncionario.getNumeroIdentificacion());
            cbxSexoEdit.setSelectedItem(selectedFuncionario.getSexo());
            cbxEstadoCivilEdit.setSelectedItem(selectedFuncionario.getEstadoCivil());
            txtFechaNacimientoEdit.setText(String.valueOf(selectedFuncionario.getFechaNacimiento()));
            txtTelefonoEdit.setText(selectedFuncionario.getTelefono());
            txtDireccionEdit.setText(selectedFuncionario.getDireccion());
            cbxTipoEdit.setSelectedItem(selectedFuncionario.getUniversidad());
            
            
            
        }
   
    }); 


    
    
    } 
    
    







    
    private LocalDate convertToDate(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
        return LocalDate.parse(dateStr, formatter);
    } catch (DateTimeParseException e) {
        e.printStackTrace();
        return null;
    }
}
    





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTVentana = new javax.swing.JTabbedPane();
        jPGuardar = new javax.swing.JPanel();
        jPOpciones = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNumeroIdentificacion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblTipoIdentificacion = new javax.swing.JLabel();
        lblNumeroIdentificacion = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        cbxTipoIdentificacion = new javax.swing.JComboBox<>();
        cbxSexo = new javax.swing.JComboBox<>();
        lblEstadoCivil = new javax.swing.JLabel();
        cbxEstadoCivil = new javax.swing.JComboBox<>();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblNivelEstudio = new javax.swing.JLabel();
        lblNombreFamiliar = new javax.swing.JLabel();
        lblApellidoFamiliar = new javax.swing.JLabel();
        lblParentesco = new javax.swing.JLabel();
        lblFechaNacimientoFamiliar = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        cbxTipo = new javax.swing.JComboBox<>();
        cbxNivelEstudio = new javax.swing.JComboBox<>();
        txtNombreFamiliar = new javax.swing.JTextField();
        txtApellidoFamiliar = new javax.swing.JTextField();
        txtFechaNacimientoFamiliar = new javax.swing.JTextField();
        txtParentesco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        jPEditarBorrar = new javax.swing.JPanel();
        jPEditarFuncionario = new javax.swing.JPanel();
        txtNombreEdit = new javax.swing.JTextField();
        txtApellidoEdit = new javax.swing.JTextField();
        txtNumeroIdentificacionEdit = new javax.swing.JTextField();
        txtTelefonoEdit = new javax.swing.JTextField();
        txtFechaNacimientoEdit = new javax.swing.JTextField();
        lblNombreEdit = new javax.swing.JLabel();
        lblApellidoEdit = new javax.swing.JLabel();
        lblTipoIdentificacionEdit = new javax.swing.JLabel();
        lblNumeroIdentificacionEdit = new javax.swing.JLabel();
        lblSexoEdit = new javax.swing.JLabel();
        cbxTipoIdentificacionEdit = new javax.swing.JComboBox<>();
        cbxSexoEdit = new javax.swing.JComboBox<>();
        lblEstadoCivilEdit = new javax.swing.JLabel();
        cbxEstadoCivilEdit = new javax.swing.JComboBox<>();
        lblFechaNacimientoEdit = new javax.swing.JLabel();
        lblTelefonoEdit = new javax.swing.JLabel();
        lblDireccionEdit = new javax.swing.JLabel();
        txtDireccionEdit = new javax.swing.JTextField();
        lblFuncionarioEdit = new javax.swing.JLabel();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        lblTituloEdit = new javax.swing.JLabel();
        lblTipoEdit = new javax.swing.JLabel();
        lblNivelEstudioEdit = new javax.swing.JLabel();
        lblNombreFamiliarEdit = new javax.swing.JLabel();
        lblApellidoFamiliarEdit = new javax.swing.JLabel();
        lblParentescoEdit = new javax.swing.JLabel();
        lblFechaNacimientoFamiliarEdit = new javax.swing.JLabel();
        txtTituloEdit = new javax.swing.JTextField();
        cbxTipoEdit = new javax.swing.JComboBox<>();
        cbxNivelEstudioEdit = new javax.swing.JComboBox<>();
        txtNombreFamiliarEdit = new javax.swing.JTextField();
        txtApellidoFamiliarEdit = new javax.swing.JTextField();
        txtFechaNacimientoFamiliarEdit = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtFuncionarioId = new javax.swing.JTextField();
        lblFuncionarioId = new javax.swing.JLabel();
        txtParentescoEdit = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite los siguientes datos del funcionario"));
        jPOpciones.setPreferredSize(new java.awt.Dimension(400, 438));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre");

        lblApellido.setText("Apellido");

        lblTipoIdentificacion.setText("Tipo Identificación");

        lblNumeroIdentificacion.setText("Número de identificación");

        lblSexo.setText("Genero");

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        cbxTipoIdentificacion.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cbxSexo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        lblEstadoCivil.setText("Estado Civil");

        cbxEstadoCivil.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        lblFechaNacimiento.setText("Fecha de nacimiento");

        lblTelefono.setText("Telefono");

        lblDireccion.setText("Dirección");

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        lblTitulo.setText("Titulo Universitario");

        lblTipo.setText("Universidad");

        lblNivelEstudio.setText("Nivel de estudio");

        lblNombreFamiliar.setText("Nombre de familiar");

        lblApellidoFamiliar.setText("Apellido de familiar");

        lblParentesco.setText("Parentesco");

        lblFechaNacimientoFamiliar.setText("Fecha de nacimiento");

        javax.swing.GroupLayout jPOpcionesLayout = new javax.swing.GroupLayout(jPOpciones);
        jPOpciones.setLayout(jPOpcionesLayout);
        jPOpcionesLayout.setHorizontalGroup(
            jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblApellido)
                            .addComponent(lblTipoIdentificacion))
                        .addGap(44, 44, 44)
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtApellido)
                            .addComponent(cbxTipoIdentificacion, 0, 176, Short.MAX_VALUE)))
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumeroIdentificacion)
                            .addComponent(lblSexo)
                            .addComponent(lblEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaNacimiento)
                            .addComponent(lblTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroIdentificacion)
                            .addComponent(cbxSexo, 0, 175, Short.MAX_VALUE)
                            .addComponent(cbxEstadoCivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaNacimiento)
                            .addComponent(txtTelefono))))
                .addGap(41, 41, 41)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDireccion)
                    .addComponent(lblTitulo)
                    .addComponent(lblNivelEstudio)
                    .addComponent(lblNombreFamiliar)
                    .addComponent(lblApellidoFamiliar)
                    .addComponent(lblParentesco)
                    .addComponent(lblFechaNacimientoFamiliar)
                    .addComponent(lblTipo))
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaNacimientoFamiliar))
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccion)
                            .addComponent(txtTitulo)
                            .addComponent(cbxTipo, 0, 183, Short.MAX_VALUE)
                            .addComponent(cbxNivelEstudio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombreFamiliar)
                            .addComponent(txtApellidoFamiliar)
                            .addComponent(txtParentesco))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addContainerGap())
        );
        jPOpcionesLayout.setVerticalGroup(
            jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPOpcionesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombre)
                    .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDireccion)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoIdentificacion)
                    .addComponent(cbxTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroIdentificacion)
                    .addComponent(txtNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNivelEstudio)
                    .addComponent(cbxNivelEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreFamiliar)
                    .addComponent(txtNombreFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoCivil)
                    .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoFamiliar)
                    .addComponent(txtApellidoFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParentesco)
                    .addComponent(txtParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNacimientoFamiliar)
                    .addComponent(txtFechaNacimientoFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jScrollPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        tblFuncionarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblFuncionarios.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFuncionarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblFuncionarios.setGridColor(new java.awt.Color(102, 102, 102));
        tblFuncionarios.setRowHeight(22);
        tblFuncionarios.setSelectionBackground(new java.awt.Color(153, 147, 240));
        tblFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblFuncionarios.setShowGrid(true);
        tblFuncionarios.setSize(new java.awt.Dimension(1, 1));
        jScrollPane1.setViewportView(tblFuncionarios);

        javax.swing.GroupLayout jPGuardarLayout = new javax.swing.GroupLayout(jPGuardar);
        jPGuardar.setLayout(jPGuardarLayout);
        jPGuardarLayout.setHorizontalGroup(
            jPGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPGuardarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPGuardarLayout.setVerticalGroup(
            jPGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPGuardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
        );

        jScrollPane1.getAccessibleContext().setAccessibleParent(jPGuardar);

        jTVentana.addTab("Crear Funcionario", jPGuardar);

        jPEditarFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite los siguientes datos del funcionario"));

        txtNombreEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEditActionPerformed(evt);
            }
        });

        lblNombreEdit.setText("Nombre");

        lblApellidoEdit.setText("Apellido");

        lblTipoIdentificacionEdit.setText("Tipo Identificación");

        lblNumeroIdentificacionEdit.setText("Número de identificación");

        lblSexoEdit.setText("Genero");

        cbxTipoIdentificacionEdit.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cbxSexoEdit.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        lblEstadoCivilEdit.setText("Estado Civil");

        cbxEstadoCivilEdit.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbxEstadoCivilEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoCivilEditActionPerformed(evt);
            }
        });

        lblFechaNacimientoEdit.setText("Fecha de nacimiento");

        lblTelefonoEdit.setText("Telefono");

        lblDireccionEdit.setText("Direccion");

        txtDireccionEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionEditActionPerformed(evt);
            }
        });

        lblFuncionarioEdit.setText("Funcionarios");

        cbxFuncionarios.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbxFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFuncionariosActionPerformed(evt);
            }
        });

        lblTituloEdit.setText("Titulo Universitario");

        lblTipoEdit.setText("Universidad");

        lblNivelEstudioEdit.setText("Nivel de estudio");

        lblNombreFamiliarEdit.setText("Nombre de familiar");

        lblApellidoFamiliarEdit.setText("Apellido de familiar");

        lblParentescoEdit.setText("Parentesco");

        lblFechaNacimientoFamiliarEdit.setText("Fecha de nacimiento");

        txtFechaNacimientoFamiliarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoFamiliarEditActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblFuncionarioId.setText("id Funcionario");

        javax.swing.GroupLayout jPEditarFuncionarioLayout = new javax.swing.GroupLayout(jPEditarFuncionario);
        jPEditarFuncionario.setLayout(jPEditarFuncionarioLayout);
        jPEditarFuncionarioLayout.setHorizontalGroup(
            jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                        .addComponent(lblTipoIdentificacionEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                                .addComponent(lblDireccionEdit)
                                .addGap(87, 87, 87))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEditarFuncionarioLayout.createSequentialGroup()
                                .addComponent(lblTituloEdit)
                                .addGap(34, 34, 34)))
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFuncionarioId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDireccionEdit)
                                .addComponent(txtTituloEdit)
                                .addComponent(cbxTipoEdit, 0, 178, Short.MAX_VALUE)
                                .addComponent(cbxNivelEstudioEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombreFamiliarEdit)
                                .addComponent(txtApellidoFamiliarEdit)
                                .addComponent(txtFechaNacimientoFamiliarEdit))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEditarFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEditarFuncionarioLayout.createSequentialGroup()
                                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreEdit)
                                    .addComponent(lblSexoEdit)
                                    .addComponent(lblFuncionarioEdit)
                                    .addComponent(lblApellidoEdit))
                                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                                        .addGap(280, 280, 280)
                                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFechaNacimientoFamiliarEdit)
                                            .addComponent(lblFuncionarioId)))
                                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtApellidoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxTipoIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnActualizar)
                                        .addGap(75, 75, 75)
                                        .addComponent(btnEliminar))
                                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                                        .addGap(281, 281, 281)
                                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNombreFamiliarEdit)
                                            .addComponent(lblApellidoFamiliarEdit)
                                            .addComponent(lblParentescoEdit)
                                            .addComponent(lblTipoEdit)
                                            .addComponent(lblNivelEstudioEdit))
                                        .addGap(29, 29, 29)
                                        .addComponent(txtParentescoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEditarFuncionarioLayout.createSequentialGroup()
                                    .addComponent(lblTelefonoEdit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEditarFuncionarioLayout.createSequentialGroup()
                                    .addComponent(lblFechaNacimientoEdit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cbxSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                                    .addComponent(lblNumeroIdentificacionEdit)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNumeroIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEditarFuncionarioLayout.createSequentialGroup()
                                    .addComponent(lblEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(144, 144, 144))
        );
        jPEditarFuncionarioLayout.setVerticalGroup(
            jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNivelEstudioEdit)
                            .addComponent(cbxNivelEstudioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreFamiliarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreFamiliarEdit)
                            .addComponent(txtApellidoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellidoEdit))
                        .addGap(15, 15, 15))
                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFuncionarioEdit)
                            .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccionEdit)
                            .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTituloEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTituloEdit))
                        .addGap(18, 18, 18)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTipoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreEdit))
                        .addGap(65, 65, 65)))
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblApellidoFamiliarEdit)
                        .addComponent(txtApellidoFamiliarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxTipoIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPEditarFuncionarioLayout.createSequentialGroup()
                        .addComponent(lblTipoIdentificacionEdit)
                        .addGap(4, 4, 4)))
                .addGap(18, 18, 18)
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroIdentificacionEdit)
                    .addComponent(txtNumeroIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblParentescoEdit)
                    .addComponent(txtParentescoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexoEdit)
                    .addComponent(cbxSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNacimientoFamiliarEdit)
                    .addComponent(txtFechaNacimientoFamiliarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoCivilEdit)
                    .addComponent(cbxEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuncionarioId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFuncionarioId))
                .addGap(18, 18, 18)
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimientoEdit)
                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefonoEdit)
                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar))
                .addGap(115, 115, 115))
        );

        javax.swing.GroupLayout jPEditarBorrarLayout = new javax.swing.GroupLayout(jPEditarBorrar);
        jPEditarBorrar.setLayout(jPEditarBorrarLayout);
        jPEditarBorrarLayout.setHorizontalGroup(
            jPEditarBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEditarBorrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPEditarFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPEditarBorrarLayout.setVerticalGroup(
            jPEditarBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEditarBorrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPEditarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 589, Short.MAX_VALUE))
        );

        jTVentana.addTab("Actualizar Funcionario", jPEditarBorrar);

        getContentPane().add(jTVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        if (txtNombre.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Nombre");
        txtNombre.requestFocus();
        return;
    }

    if (txtApellido.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Apellido");
        txtApellido.requestFocus();
        return;
    }
    
    if (cbxTipoIdentificacion.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione Tipo de Identificación");
        cbxTipoIdentificacion.requestFocus();
        return;
    }

    if (txtNumeroIdentificacion.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Número de Identificación");
        txtNumeroIdentificacion.requestFocus();
        return;
    }
    
    if (cbxSexo.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione Género");
        cbxSexo.requestFocus();
        return;
    }
    
    if (cbxEstadoCivil.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione Estado Civil");
        cbxEstadoCivil.requestFocus();
        return;
    }
    
    
    LocalDate fechaNacimiento = convertToDate(txtFechaNacimiento.getText().trim());
    if (fechaNacimiento == null) {
        JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida. Use el formato yyyy-MM-dd.");
        txtFechaNacimiento.requestFocus();
        return;
    }
    
     if (txtTelefono.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Teléfono");
        txtTelefono.requestFocus();
        return;
    }
     
      if (txtDireccion.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Dirección");
        txtDireccion.requestFocus();
        return;
    }
      
      if (txtTitulo.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Titulo Universitario");
        txtTitulo.requestFocus();
        return;
    }
    
      if (cbxTipo.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione tipo de Universidad");
        cbxTipo.requestFocus();
        return;
    }
      
      if (cbxNivelEstudio.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione nivel actual de estudio");
        cbxNivelEstudio.requestFocus();
        return;
    }
    
    

    
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(txtNombre.getText().trim());
        funcionario.setApellido(txtApellido.getText().trim());
        funcionario.setNumeroIdentificacion(txtNumeroIdentificacion.getText().trim());
        funcionario.setSexo((Sexo) cbxSexo.getSelectedItem());
        funcionario.setEstadoCivil((EstadoCivil) cbxEstadoCivil.getSelectedItem());
        funcionario.setTipoIdentificacion((TipoIdentificacion) cbxTipoIdentificacion.getSelectedItem());
        funcionario.setUniversidad((Universidad) cbxTipo.getSelectedItem());
        funcionario.setFechaNacimiento(fechaNacimiento);
        funcionario.setTelefono(txtTelefono.getText().trim());
        funcionario.setDireccion(txtDireccion.getText().trim());

    try {
        
        funcionarioController.create(funcionario);
        
        
        txtNombre.setText("");
        txtApellido.setText("");
        txtNumeroIdentificacion.setText("");
        txtFechaNacimiento.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        
        
        listFuncionarios();
        
        
        JOptionPane.showMessageDialog(null, "Funcionario creado con éxito");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "El funcionario no pudo ser creado");
    }
// TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtNombreEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEditActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        if (txtFuncionarioId.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una funcionario de la lista");
        }
        
        if (txtNombreEdit.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Nombre");
        txtNombreEdit.requestFocus();
        return;
    }

    if (txtApellidoEdit.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Apellido");
        txtApellidoEdit.requestFocus();
        return;
    }
    
    if (cbxTipoIdentificacionEdit.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione Tipo de Identificación");
        cbxTipoIdentificacionEdit.requestFocus();
        return;
    }

    if (txtNumeroIdentificacionEdit.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Número de Identificación");
        txtNumeroIdentificacionEdit.requestFocus();
        return;
    }
    
    if (cbxSexoEdit.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione Género");
        cbxSexoEdit.requestFocus();
        return;
    }
    
    if (cbxEstadoCivilEdit.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione Estado Civil");
        cbxEstadoCivilEdit.requestFocus();
        return;
    }
    
    
    LocalDate fechaNacimiento = convertToDate(txtFechaNacimientoEdit.getText().trim());
    if (fechaNacimiento == null) {
        JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida. Use el formato yyyy-MM-dd.");
        txtFechaNacimientoEdit.requestFocus();
        return;
    }
    
     if (txtTelefonoEdit.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Teléfono");
        txtTelefonoEdit.requestFocus();
        return;
    }
     
      if (txtDireccionEdit.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Dirección");
        txtDireccionEdit.requestFocus();
        return;
    }
      
      if (txtTituloEdit.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Digite Titulo Universitario");
        txtTituloEdit.requestFocus();
        return;
    }
    
      if (cbxTipoEdit.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione tipo de Universidad");
        cbxTipoEdit.requestFocus();
        return;
    }
      
      if (cbxNivelEstudioEdit.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione nivel actual de estudio");
        cbxNivelEstudioEdit.requestFocus();
        return;
    }
    
    

    
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(txtNombreEdit.getText().trim());
        funcionario.setApellido(txtApellidoEdit.getText().trim());
        funcionario.setNumeroIdentificacion(txtNumeroIdentificacionEdit.getText().trim());
        funcionario.setSexo((Sexo) cbxSexoEdit.getSelectedItem());
        funcionario.setEstadoCivil((EstadoCivil) cbxEstadoCivilEdit.getSelectedItem());
        funcionario.setTipoIdentificacion((TipoIdentificacion) cbxTipoIdentificacionEdit.getSelectedItem());
        funcionario.setUniversidad((Universidad) cbxTipoEdit.getSelectedItem());
        funcionario.setFechaNacimiento(fechaNacimiento);
        funcionario.setTelefono(txtTelefonoEdit.getText().trim());
        funcionario.setDireccion(txtDireccionEdit.getText().trim());
        
        int opt = JOptionPane.showConfirmDialog(null, "Desea actualizar el funcionario", "Confirmar salida",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opt == JOptionPane.YES_OPTION) {
    try {
        funcionarioController.updateFuncionario(Integer.parseInt(txtFuncionarioId.getText()), funcionario);
        txtFuncionarioId.setText("");
        txtNombreEdit.setText("");
        txtApellidoEdit.setText("");
        txtNumeroIdentificacionEdit.setText("");
        cbxSexoEdit.setSelectedIndex(0);
        cbxEstadoCivilEdit.setSelectedIndex(0);
        cbxTipoIdentificacionEdit.setSelectedIndex(0);
        cbxTipoEdit.setSelectedIndex(0);
        txtFechaNacimientoEdit.setText("");
        txtTelefonoEdit.setText("");
        txtDireccionEdit.setText("");
        txtTituloEdit.setText("");
        cbxNivelEstudioEdit.setSelectedIndex(0);
        listFuncionarios();
        JOptionPane.showMessageDialog(null, "Se ha actualizado el funcionario con éxito");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "No fue posible actualizar el funcionario");
    }
           
    }//GEN-LAST:event_btnActualizarActionPerformed
    }
    private void txtDireccionEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEditActionPerformed

    private void cbxEstadoCivilEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoCivilEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoCivilEditActionPerformed

    private void cbxFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFuncionariosActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
     if (txtFuncionarioId.getText().trim().length() == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione un funcionario de la lista");
        txtFuncionarioId.requestFocus();
        return;
    }
    
    int opt = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el funcionario?", 
                "Confirmar salida", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    
    if (opt == JOptionPane.YES_OPTION) {
        try {
            funcionarioController.deleteFuncionario(Integer.parseInt(txtFuncionarioId.getText()));

            txtFuncionarioId.setText("");
            txtNombreEdit.setText("");
            txtApellidoEdit.setText("");
            txtNumeroIdentificacionEdit.setText("");
            cbxSexoEdit.setSelectedIndex(0);
            cbxEstadoCivilEdit.setSelectedIndex(0);
            cbxTipoIdentificacionEdit.setSelectedIndex(0);
            cbxTipoEdit.setSelectedIndex(0);
            txtFechaNacimientoEdit.setText("");
            txtTelefonoEdit.setText("");
            txtDireccionEdit.setText("");
            txtTituloEdit.setText("");
            cbxNivelEstudioEdit.setSelectedIndex(0);
            listFuncionarios();
            JOptionPane.showMessageDialog(null, "Funcionario eliminado del sistema");
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No fue posible eliminar el funcionario");
        }
    }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtFechaNacimientoFamiliarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoFamiliarEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoFamiliarEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<EstadoCivil> cbxEstadoCivil;
    private javax.swing.JComboBox<EstadoCivil> cbxEstadoCivilEdit;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarios;
    private javax.swing.JComboBox<NivelEstudio> cbxNivelEstudio;
    private javax.swing.JComboBox<NivelEstudio> cbxNivelEstudioEdit;
    private javax.swing.JComboBox<Sexo> cbxSexo;
    private javax.swing.JComboBox<Sexo> cbxSexoEdit;
    private javax.swing.JComboBox<Universidad> cbxTipo;
    private javax.swing.JComboBox<Universidad> cbxTipoEdit;
    private javax.swing.JComboBox<TipoIdentificacion> cbxTipoIdentificacion;
    private javax.swing.JComboBox<TipoIdentificacion> cbxTipoIdentificacionEdit;
    private javax.swing.JPanel jPEditarBorrar;
    private javax.swing.JPanel jPEditarFuncionario;
    private javax.swing.JPanel jPGuardar;
    private javax.swing.JPanel jPOpciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTVentana;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblApellidoEdit;
    private javax.swing.JLabel lblApellidoFamiliar;
    private javax.swing.JLabel lblApellidoFamiliarEdit;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDireccionEdit;
    private javax.swing.JLabel lblEstadoCivil;
    private javax.swing.JLabel lblEstadoCivilEdit;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFechaNacimientoEdit;
    private javax.swing.JLabel lblFechaNacimientoFamiliar;
    private javax.swing.JLabel lblFechaNacimientoFamiliarEdit;
    private javax.swing.JLabel lblFuncionarioEdit;
    private javax.swing.JLabel lblFuncionarioId;
    private javax.swing.JLabel lblNivelEstudio;
    private javax.swing.JLabel lblNivelEstudioEdit;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreEdit;
    private javax.swing.JLabel lblNombreFamiliar;
    private javax.swing.JLabel lblNombreFamiliarEdit;
    private javax.swing.JLabel lblNumeroIdentificacion;
    private javax.swing.JLabel lblNumeroIdentificacionEdit;
    private javax.swing.JLabel lblParentesco;
    private javax.swing.JLabel lblParentescoEdit;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSexoEdit;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoEdit;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipoEdit;
    private javax.swing.JLabel lblTipoIdentificacion;
    private javax.swing.JLabel lblTipoIdentificacionEdit;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloEdit;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellidoEdit;
    private javax.swing.JTextField txtApellidoFamiliar;
    private javax.swing.JTextField txtApellidoFamiliarEdit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEdit;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaNacimientoEdit;
    private javax.swing.JTextField txtFechaNacimientoFamiliar;
    private javax.swing.JTextField txtFechaNacimientoFamiliarEdit;
    private javax.swing.JTextField txtFuncionarioId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreEdit;
    private javax.swing.JTextField txtNombreFamiliar;
    private javax.swing.JTextField txtNombreFamiliarEdit;
    private javax.swing.JTextField txtNumeroIdentificacion;
    private javax.swing.JTextField txtNumeroIdentificacionEdit;
    private javax.swing.JTextField txtParentesco;
    private javax.swing.JTextField txtParentescoEdit;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEdit;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtTituloEdit;
    // End of variables declaration//GEN-END:variables

    
};