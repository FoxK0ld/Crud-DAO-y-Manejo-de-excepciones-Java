package com.iudigital.project.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Funcionario {

    private int idFuncionario;
    private TipoIdentificacion tipoIdentificacion;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private String numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Universidad universidad;
    private List<GrupoFamiliar> gruposFamiliares;
    private List<InformacionAcademica> informacionAcademica;

    
    public Funcionario() {
    this.gruposFamiliares = new ArrayList<>();
    this.informacionAcademica = new ArrayList<>();
    
    }

    public Funcionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public List<GrupoFamiliar> getGruposFamiliares() {
        return gruposFamiliares;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }
    
    

    public void setGruposFamiliares(List<GrupoFamiliar> gruposFamiliares) {
        this.gruposFamiliares = gruposFamiliares;
    }

    public List<InformacionAcademica> getInformacionAcademica() {
        return informacionAcademica;
    }

    public void setInformacionAcademica(List<InformacionAcademica> informacionAcademica) {
        this.informacionAcademica = informacionAcademica;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + numeroIdentificacion;
    }

    public void setSexo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTipoIdentificacion(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
