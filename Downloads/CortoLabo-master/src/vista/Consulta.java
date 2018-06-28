/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
     public JLabel lblCodigo,lblPrecio,lblNombre,lblCantidad, lblTipo, lblDisponibilidad;
    public JTextField codigo,precio,nombre,cantidad;
    public JComboBox tipo;
    
    ButtonGroup Disponibilidad=new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar,eliminar,insertar,limpiar,actualizar;
    
    private static final int ANCHOC=130, ALTOC=30;
    
    DefaultTableModel tm;
    
   public Consulta(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container=getContentPane();
        container.add(lblCodigo);
        container.add(lblPrecio);
        container.add(lblNombre);
        container.add(lblCantidad);
        container.add(lblTipo);
        container.add(lblDisponibilidad);        
        container.add(codigo);
        container.add(precio);
        container.add(nombre);
        container.add(cantidad);
        container.add(tipo);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,600);
        eventos();
        
    }
    public final void agregarLabels(){
        lblCodigo=new JLabel("Codigo");
        lblPrecio=new JLabel("Precio");
        lblNombre=new JLabel("Nombre");
        lblCantidad=new JLabel("Cantidad");
        lblTipo=new JLabel("Tipo");
        lblDisponibilidad=new JLabel("Disponibilidad");
        lblCodigo.setBounds(10,10,ANCHOC,ALTOC);
        lblPrecio.setBounds(10,60,ANCHOC,ALTOC);
        lblNombre.setBounds(10,100,ANCHOC,ALTOC);
        lblCantidad.setBounds(10,140,ANCHOC,ALTOC);
        lblTipo.setBounds(10,180,ANCHOC,ALTOC);
        lblDisponibilidad.setBounds(10,220, ANCHOC, ALTOC);
    }
    public final void formulario(){
        codigo=new JTextField();
        tipo=new JComboBox();
        precio=new JTextField();
        nombre=new JTextField();
        cantidad=new JTextField();
        si=new JRadioButton("si",true);
        no=new JRadioButton("no");
        resultados=new JTable();
        buscar=new JButton("Buscar");
        insertar=new JButton("Insertar");
        eliminar=new JButton("Eliminar");
        actualizar=new JButton("Actualizar");
        limpiar=new JButton("Limpiar");
        
        table=new JPanel();
        
        tipo.addItem("Fruta");
        tipo.addItem("Verdura");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");
        
        Disponibilidad=new ButtonGroup();
        Disponibilidad.add(si);
        Disponibilidad.add(no);
        
        codigo.setBounds(140,10,ANCHOC,ALTOC);
        tipo.setBounds(140,180,ANCHOC,ALTOC);
        precio.setBounds(140,60,ANCHOC,ALTOC);
        nombre.setBounds(140,100,ANCHOC,ALTOC);
        cantidad.setBounds(140,140,ANCHOC,ALTOC);
        si.setBounds(140,220,50,ALTOC);
        no.setBounds(210,220,50,ALTOC);
        
        buscar.setBounds(300,10, ANCHOC,ALTOC);
        insertar.setBounds(10,250,ANCHOC,ALTOC);
        actualizar.setBounds(150,250,ANCHOC,ALTOC);
        eliminar.setBounds(300,250,ANCHOC,ALTOC);
        limpiar.setBounds(450,250,ANCHOC,ALTOC);
        resultados=new JTable();
        table.setBounds(10,290,500,200);
        table.add(new JScrollPane(resultados));
    }
    public void llenarTabla(){
        tm=new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Tipo");
        
        tm.addColumn("Precio");
        tm.addColumn("Cantidad");
        tm.addColumn("Disponibilidad");
        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();
        
        for(Filtro fi : filtros){
            tm.addRow(new Object[]{fi.getCodigo(), fi.getNombre(), fi.getTipo(),fi.getPrecio(),fi.getCantidad(), fi.isDisponibilidad()});
        }
        
        resultados.setModel(tm);
    }
    public void eventos(){
        insertar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            FiltroDao fd = new FiltroDao();
            Filtro f = new Filtro( nombre.getText(),codigo.getText(),tipo.getSelectedItem().toString(),Integer.parseInt(cantidad.getText()),Double.parseDouble(precio.getText()), true);
            
           
            if(no.isSelected()){
                f.setDisponibilidad(false);
            }
            
            if(fd.create(f)){
                JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrio un problema al crear el producto");
            }
        }
    });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            FiltroDao fd = new FiltroDao();
            Filtro f = new Filtro( nombre.getText(),codigo.getText(),tipo.getSelectedItem().toString(),Integer.parseInt(cantidad.getText()),Double.parseDouble(precio.getText()), true);
            
            
            if(no.isSelected()){
                f.setDisponibilidad(false);
            }
            
            if(fd.update(f)){
                JOptionPane.showMessageDialog(null, "Producto modificado con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrio un problema al modificar el producto");
            }
        }
        });
        
        eliminar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            FiltroDao fd = new FiltroDao();
            if(fd.delete(codigo.getText())){
                JOptionPane.showMessageDialog(null, "Producto Eliminado con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrio problema al eliminar el producto");
            }
        }   
    });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd = new FiltroDao();
                if(fd.delete(codigo.getText())){
                    JOptionPane.showMessageDialog(null, "Producto eliminado");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio problema ");
                }
            }
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "no se encontro el producto ");
                }else{
                    codigo.getText();
                    Double.parseDouble(precio.getText());
                    nombre.getText();
                    Integer.parseInt(cantidad.getText());
                    tipo.getSelectedItem().toString();
                   
                    
                    if(f.isDisponibilidad()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                }
            }
        });
        
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                limpiarCampos();
            }
        });
    }
        
        public void limpiarCampos(){
            codigo.setText("");
            tipo.setSelectedItem("FRAM");
            precio.setText("");
            cantidad.setText("");
            nombre.setText("");
        }
         public static void main(String[] args){
            java.awt.EventQueue.invokeLater(new Runnable(){
                @Override
                public void run(){
                    new Consulta().setVisible(true);
                }
            });
        }

}
