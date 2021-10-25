package dao;

import java.util.ArrayList;


public interface GenericDAO<T> {
	public ArrayList<T> findAll();

}
