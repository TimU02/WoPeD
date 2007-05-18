package org.woped.quantana.gui;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JLabel;

import org.woped.editor.utilities.Messages;
import org.woped.quantana.simulation.Server;
import org.woped.quantana.simulation.Simulator;

public class DetailsDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH	= 400;
	
	private static final int HEIGHT	= 300;
	
	private Dialog owner;
	
	private Container contentPane;
	
	private String servName;
	
	private Simulator sim;
	
	private double clock;
	
	private Server server;
	
	private boolean isProcess = false;
	
	private JLabel lblDesc_1;
	private JLabel lblDetail_1;
	
	private JLabel lblDesc_2;
	private JLabel lblDetail_2;
	
	private JLabel lblDesc_3;
	private JLabel lblDetail_3;
	
	private JLabel lblDesc_4;
	private JLabel lblDetail_4;
	
	private JLabel lblDesc_5;
	private JLabel lblDetail_5;
	
	private JLabel lblDesc_6;
	private JLabel lblDetail_6;
	
	private JLabel lblDesc_7;
	private JLabel lblDetail_7;
	
	private JLabel lblDesc_8;
	private JLabel lblDetail_8;
	
	public DetailsDialog(Dialog owner, String sname){
		super(owner, Messages.getString("QuantAna.Simulation.Details") + " " + sname, true);
		this.owner = owner;
		servName = sname;
		
		getServer();
		
		initialize();
	}
	
	private void initialize(){
		contentPane = this.getContentPane();
		
		lblDesc_1 = new JLabel();
		lblDesc_1.setPreferredSize(new Dimension(200, 20));
		lblDetail_1 = new JLabel();
		lblDetail_1.setPreferredSize(new Dimension(80, 20));
		lblDesc_2 = new JLabel();
		lblDesc_2.setPreferredSize(new Dimension(200, 20));
		lblDetail_2 = new JLabel();
		lblDetail_2.setPreferredSize(new Dimension(80, 20));
		lblDesc_3 = new JLabel();
		lblDesc_3.setPreferredSize(new Dimension(200, 20));
		lblDetail_3 = new JLabel();
		lblDetail_3.setPreferredSize(new Dimension(80, 20));
		lblDesc_4 = new JLabel();
		lblDesc_4.setPreferredSize(new Dimension(200, 20));
		lblDetail_4 = new JLabel();
		lblDetail_4.setPreferredSize(new Dimension(80, 20));
		lblDesc_5 = new JLabel();
		lblDesc_5.setPreferredSize(new Dimension(200, 20));
		lblDetail_5 = new JLabel();
		lblDetail_5.setPreferredSize(new Dimension(80, 20));
		lblDesc_6 = new JLabel();
		lblDesc_6.setPreferredSize(new Dimension(200, 20));
		lblDetail_6 = new JLabel();
		lblDetail_6.setPreferredSize(new Dimension(80, 20));
		lblDesc_7 = new JLabel();
		lblDesc_7.setPreferredSize(new Dimension(200, 20));
		lblDetail_7 = new JLabel();
		lblDetail_7.setPreferredSize(new Dimension(80, 20));
		lblDesc_8 = new JLabel();
		lblDesc_8.setPreferredSize(new Dimension(200, 20));
		lblDetail_8 = new JLabel();
		lblDetail_8.setPreferredSize(new Dimension(80, 20));
		
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		contentPane.add(lblDesc_1, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 0;
		contentPane.add(lblDetail_1, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 1;
		contentPane.add(lblDesc_2, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 1;
		contentPane.add(lblDetail_2, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 2;
		contentPane.add(lblDesc_3, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 2;
		contentPane.add(lblDetail_3, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 3;
		contentPane.add(lblDesc_4, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 3;
		contentPane.add(lblDetail_4, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 4;
		contentPane.add(lblDesc_5, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 4;
		contentPane.add(lblDetail_5, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 5;
		contentPane.add(lblDesc_6, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 5;
		contentPane.add(lblDetail_6, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 6;
		contentPane.add(lblDesc_7, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 6;
		contentPane.add(lblDetail_7, constraints);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 7;
		contentPane.add(lblDesc_8, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 7;
		contentPane.add(lblDetail_8, constraints);
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 8;
		contentPane.add(new JLabel(), constraints);
		
		if (server == null && isProcess){
			// Prozess-Statistik
			lblDesc_1.setText(Messages.getString("QuantAna.Simulation.Details.Clock"));
			lblDetail_1.setText(String.format("%,.2f", sim.getClock()));
			lblDesc_2.setText(Messages.getString("QuantAna.Simulation.Details.FinishedCases"));
			lblDetail_2.setText(String.format("%d", sim.getFinishedCases()));
			lblDesc_3.setText(Messages.getString("QuantAna.Simulation.Details.MaxCasesInSystem"));
			lblDetail_3.setText(String.format("%d", sim.getMaxNumCasesInSystem()));
		} else if (server != null){
			// Server-Statistik
			lblDesc_1.setText(Messages.getString("QuantAna.Simulation.Details.Utilization"));
			lblDetail_1.setText(String.format("%,.2f", server.getBusy() / clock));
			lblDesc_2.setText(Messages.getString("QuantAna.Simulation.Details.ZeroDelays"));
			lblDetail_2.setText(String.format("%d", server.getZeroDelays()));
			lblDesc_3.setText(Messages.getString("QuantAna.Simulation.Details.NumCalls"));
			lblDetail_3.setText(String.format("%d", server.getNumCalls()));
			lblDesc_4.setText(Messages.getString("QuantAna.Simulation.Details.NumAccess"));
			lblDetail_4.setText(String.format("%d", server.getNumAccess()));
			lblDesc_5.setText(Messages.getString("QuantAna.Simulation.Details.NumDeparture"));
			lblDetail_5.setText(String.format("%d", server.getNumDeparture()));
			lblDesc_6.setText(Messages.getString("QuantAna.Simulation.Details.MaxQueueLength"));
			lblDetail_6.setText(String.format("%d", server.getMaxQueueLength()));
			lblDesc_7.setText(Messages.getString("QuantAna.Simulation.Details.MaxCasesParallel"));
			lblDetail_7.setText(String.format("%d", server.getMaxNumCasesInParallel()));
			lblDesc_8.setText(Messages.getString("QuantAna.Simulation.Details.MaxWaitTime"));
			lblDetail_8.setText(String.format("%,.2f", server.getMaxWaitTimeOfCase()));
		}
		
		Rectangle bounds = owner.getBounds();
		int x = (bounds.width - WIDTH)/2 + bounds.x;
		int y = (bounds.height - HEIGHT)/2 + bounds.y;
		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setVisible(true);
	}
	
	private void getServer(){
		String key = produceID(servName);
		sim = ((QuantitativeSimulationDialog)owner).getSimulator();
		clock = sim.getClock();
		
		if (key.equals(Messages.getString("QuantAna.Simulation.Process"))){
			server = null;
			isProcess = true;
		} else {
			isProcess = false;
			server = sim.getServerList().get(key);
		}
	}
	
	private String produceID(String key) {
		if (key.equals("Protocol") || key.equals("Process"))
			return key;
		else
			return key.substring(key.indexOf("(") + 1, key.indexOf(")"));
	}
}