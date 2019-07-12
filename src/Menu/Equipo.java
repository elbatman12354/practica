/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 *
 * @author Dell
 */
public class Equipo {
    private int id_equipo;
    private String nombre;
    private String promocion;
    private String url_imagen_equipo;

    public Equipo() {
    }

    public Equipo(int id_equipo, String nombre, String promocion, String url_equipo) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.promocion = promocion;
        this.url_imagen_equipo = url_imagen_equipo;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String geturl_imagen_equipo() {
        return url_imagen_equipo;
    }

    public void setUrl_equipo(String url_imagen_equipo) {
        this.url_imagen_equipo = url_imagen_equipo;
    }

    @Override
    public String toString() {
        return "id_equipo{"+ "id_equipo"+ id_equipo +"nombre" + nombre + "promocion" + promocion +"url_imagen_equipo"+ url_imagen_equipo +'}';
    }

  
    
    }

