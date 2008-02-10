package org.woped.core.model.bpel;

/*
 * @author: Alexander Ro�wog
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class UddiVariableList 
{
	private HashSet<UddiVariable> _list = new HashSet<UddiVariable>();
	
	public UddiVariableList()
	{
		_list.add(new UddiVariable("SAP", "http://udditest.sap.com/uddi/api/inquiry/"));
		_list.add(new UddiVariable("Microsoft", "http://uddi.microsoft.com/inquire"));
	}
	
	public void addVariable(String name, String url)
	{
		this._list.add(new UddiVariable(name,url));
	}
	
	public String[] getVariableNameArray() {
		String[] list = new String[this._list.size()];
		Iterator<UddiVariable> iter = this._list.iterator();
		int i = 0;
		while (iter.hasNext()) {
			list[i] = iter.next().getName();
			i++;
		}
		return list;
	}
	
	public HashSet<UddiVariable> getUddiVariableList()
	{
		return this._list;
	}
	
	public HashMap<String, String> getListOfVariablesWithURL() {
		HashMap<String, String> list = new HashMap<String, String>();
		Iterator<UddiVariable> iter = this._list.iterator();
		while (iter.hasNext()) {
			UddiVariable tmp = iter.next();
			list.put(tmp.getName(), tmp.getURL());
		}
		return list;
	}
	
	public UddiVariable findUddiVariableByName(String name)
	{
		Iterator<UddiVariable> iter = this._list.iterator();
		while (iter.hasNext()) {
			UddiVariable tmp = iter.next();
			if(tmp.equalByName(name)) return tmp;
		}		
		return null;
	}
}