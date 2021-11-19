/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;
import java.util.Vector;


/**
 *
 * @author briza
 */
public class IndiceMax {

    int Id;
    String Nombre;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return this.Nombre;
    }

    public Vector<IndiceMax> mostrarIndices() {
        Vector<IndiceMax> indice = new Vector<IndiceMax>();
        IndiceMax dat = null;
        dat = new IndiceMax();
        dat.setId(0);
        dat.setNombre("Seleccione usuario");
        indice.add(dat);

        dat = new IndiceMax();
        dat.setId(1);
        dat.setNombre("Tareas asignadas");
        indice.add(dat);

        dat = new IndiceMax();
        dat.setId(2);
        dat.setNombre("Tareas vencidas");
        indice.add(dat);

        return indice;
    }
}
