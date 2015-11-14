package com.petcoccolati.bd;

import java.util.ArrayList;
import java.util.List;

public class PoolConection {

	private static final int TAMANIO_POOL = 5;
	
	private List<Conection> conexionesDisponibles;
	private List<Conection> conexionesUso;
	
	public PoolConection(){
		conexionesDisponibles = new ArrayList<Conection>();
		conexionesUso = new ArrayList<Conection>();
		
		for(int i=1; i<=TAMANIO_POOL; i++){
			conexionesDisponibles.add(new Conection(i+""));
		}
	}
	
	public Conection getConexion(){
		Conection conexion;
		if(conexionesDisponibles.size()>0){
			conexion = conexionesDisponibles.remove(0);
		}else{
			conexion = new Conection("Nueva");
		}
		conexionesUso.add(conexion);
		return conexion;
	}
	
	public void liberarConexion(Conection conexion){
		conexionesUso.remove(conexion);
		if(conexionesDisponibles.size()<TAMANIO_POOL){
			conexionesDisponibles.add(conexion);
		}
	}
}
