import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.net.URL;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame implements ActionListener {

	JPanel show,billPanel,dPanel,f1,f2,f3,f4,f5,f6;
	JScrollPane sp1,sp2,sp3,sp4,sp5;
	JLabel fnamee,lnamee,mail,iCode,qty,rIC,rIN,rQ,rP,rD,rDQ,rmsg,rrIC,rrQ,rrmsg,totalPrice,totalQty,usIC,uIC,uIN,uP,uQ,uD,uDQ,uh,umsg;
	JTextField famt,fname,lname,email,opswd,npswd,cpswd,itemCode,quantity,recordIC,recordIN,recordQ,recordP,recordD,recordDQ,rrecordIC,rrecordQ,search,search1,tPrice,tQty,updatesIC,updateIC,updateIN,updateQ,updateP,updateD,updateDQ;
	JTable bTable,stTable,slTable,nTable;
	JButton pchange,addd,remove,done,sadd,sremove,rSave,rRemove,go,go1,empty,cncl,cnfm,back,notify,update,uSearch,uupdate,print;
	JDialog recordAdd,recordRemove,recordUpdate;
	CardLayout cl;
	String idd,s;
	
	
	
	public MainView(String id) {		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState()|this.MAXIMIZED_BOTH);
		setLayout(null);
		setTitle("Billing Management Software- HOME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		idd=id;
		ResultSet rs = null;
		Connection con=null;
		Statement st = null;
		
		cl=new CardLayout();
		show = new JPanel(cl);
		
		
		
		// OPTIONS BUTTON PANELS
		JPanel bPanell = new JPanel(new GridLayout(11,1));
		bPanell.setBackground(Color.LIGHT_GRAY);
		bPanell.setBounds(0,109,0,0);
		bPanell.setSize(200,600);
		bPanell.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		JButton billing = new JButton("BILLING");
		JButton sales = new JButton("SALES");
		JButton stock = new JButton("STOCK DETAILS");
		notify = new JButton("NOTIFICATIONS");
		JButton admin = new JButton("ADMIN");
		JButton logout = new JButton("LOGOUT");
		JButton exit = new JButton("EXIT");
		bPanell.add(billing);
		bPanell.add(sales);
		bPanell.add(stock);
		bPanell.add(notify);
		bPanell.add(admin);
		bPanell.add(logout);
		bPanell.add(exit);
		add(bPanell,BorderLayout.WEST);

		
		
		//BILLING PANEL
		f2=new JPanel();
		f2.setLayout(null);
		JLabel h2 = new JLabel("BILLING");
		h2.setFont(new Font("Serif",Font.BOLD,25));
		h2.setBounds(420,20,200,20);
		bTable = new JTable();
		bTable.setEnabled(false);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
				st = con.createStatement();
				rs=st.executeQuery("select bill.ItemCode,bill.ItemName,bill.Quantity,stock.ItemPrice,bill.Price from bill,stock where bill.ItemCode=stock.ItemCode");
				bTable.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		 bTable.setFont(new Font("Serif",Font.BOLD,15));
		 bTable.setRowHeight(30);
		 ((DefaultTableCellRenderer)bTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
		 sp2 = new JScrollPane(bTable);
		 sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 sp2.setBounds(0,70,963,524);
		 f2.add(sp2);
		 
		 
		 
		 
		//ADD-REMOVE PANEL(BILLING)
		billPanel = new JPanel();
		billPanel.setLayout(null);
		billPanel.setBackground(Color.LIGHT_GRAY);
		billPanel.setBounds(962,0,0,0);
		billPanel.setSize(200,600);
		billPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		iCode = new JLabel("Item Code:");
		iCode.setBounds(10,0,100,50);
		iCode.setFont(new Font("Times New Roman",Font.PLAIN,16));
		billPanel.add(iCode);
		itemCode = new JTextField(15);
		itemCode.setBounds(10,40,180,30);
		billPanel.add(itemCode);
		qty = new JLabel("Quantity:");
		qty.setFont(new Font("Times New Roman",Font.PLAIN,16));
		qty.setBounds(10,70,100,50);
		billPanel.add(qty);
		quantity = new JTextField(15);
		quantity.setBounds(10,110,180,30);
		billPanel.add(quantity);
		addd = new JButton("ADD");
		addd.setBounds(10,160,70,25);
		addd.addActionListener(this);
		remove = new JButton("REMOVE");
		remove.setBounds(100,160,90,25);
		remove.addActionListener(this);
		done = new JButton("DONE");
		done.setBounds(60,220,80,25);
		done.addActionListener(this);
		empty = new JButton("NEW BILL");
		empty.setBounds(50,260,100,25);
		empty.addActionListener(this);
		billPanel.add(addd);
		billPanel.add(remove);
		billPanel.add(done);
		billPanel.add(empty);
		
		
		
		//BILL TABLE AREA
		dPanel = new JPanel();
		dPanel.setLayout(null);
		JLabel bil = new JLabel("BILL");
		bil.setFont(new Font("Serif",Font.BOLD,25));
		bil.setBounds(500,20,200,20);
		dPanel.add(bil);
		totalPrice = new JLabel("Total Price:");
		totalPrice.setFont(new Font("Serif",Font.BOLD,20));
		totalPrice.setBounds(200,100,200,20);
		dPanel.add(totalPrice);
		tPrice = new JTextField();
		tPrice.setBounds(200,130,210,25);
		tPrice.setFont(new Font("Serif",Font.PLAIN,20));
		dPanel.add(tPrice);
		totalQty = new JLabel("Total Items:");
		totalQty.setFont(new Font("Serif",Font.BOLD,20));
		totalQty.setBounds(650,100,200,20);
		dPanel.add(totalQty);
		tQty = new JTextField();
		tQty.setBounds(650,130,210,25);
		tQty.setFont(new Font("Serif",Font.PLAIN,20));
		dPanel.add(tQty);
		cnfm = new JButton("CONFIRM");
		cnfm.setBounds(350,300,100,25);
		cnfm.addActionListener(this);
		dPanel.add(cnfm);
		print = new JButton("PRINT");
		print.setBounds(500,300,100,25);
		print.addActionListener(new btnPrintAction());
		dPanel.add(print);
		back = new JButton("BACK");
		back.setBounds(650,300,100,25);
		back.addActionListener(this);
		dPanel.add(back);
		show.add(f2,"Bill");
		show.add(dPanel,"Done");
		f2.add(h2);
		f2.add(billPanel,BorderLayout.EAST);
		
		
		
		//SALES RECORD AREA
		f3=new JPanel();
		f3.setLayout(null);
		JLabel h3 = new JLabel("SALES RECORD");
		h3.setFont(new Font("Serif",Font.BOLD,25));
		h3.setBounds(500,20,200,20);
		f3.add(h3);
		JLabel l = new JLabel("Total Amount:       Rs.");
		l.setBounds(60,20,120,24);
		f3.add(l);
		famt = new JTextField("");
		famt.setBounds(180,20,150,25);
		famt.setEditable(false);
		f3.add(famt);
		search1 = new JTextField("search");
		search1.setBounds(900,20,150,25);
		f3.add(search1);
		go1 = new JButton("GO");
		go1.setBounds(1060,20,55,24);
		go1.addActionListener(this);
		f3.add(go1);
		 slTable = new JTable();
		 slTable.setEnabled(false);
		 try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
				st = con.createStatement();
				rs=st.executeQuery("select * from sale");
				slTable.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		 slTable.setFont(new Font("Serif",Font.BOLD,15));
		 slTable.setRowHeight(30);
		 ((DefaultTableCellRenderer)slTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
		 sp3 = new JScrollPane(slTable);
		 sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 sp3.setBounds(0,70,1164,524);
		 f3.add(sp3);
		show.add(f3,"Sales");
		
		
		
		
		
		//STOCKS DETAIL
		f4=new JPanel();
		f4.setLayout(null);
		JLabel h4 = new JLabel("STOCK DETAILS");
		h4.setFont(new Font("Serif",Font.BOLD,25));
		h4.setBounds(500,20,250,20);
		f4.add(h4);
		search = new JTextField("search");
		search.setBounds(100,20,150,25);
		f4.add(search);
		go = new JButton("GO");
		go.setBounds(260,20,55,24);
		go.addActionListener(this);
		f4.add(go);
		sadd = new JButton("ADD");
		sadd.setBounds(865,20,90,25);
		sadd.addActionListener(this);
		f4.add(sadd);
		
		
		
		
		//ADD ITEM DIALOG BOX
		recordAdd = new JDialog();
		recordAdd.setLayout(null);
		recordAdd.setTitle("Add Item");
		recordAdd.setBounds(600,200,400,310);
		recordAdd.setModal(true);
		rIC = new JLabel("Item Code");
		rIC.setBounds(15,10,100,20);
		recordAdd.add(rIC);
		recordIC = new JTextField();
		recordIC.setBounds(15,35,150,25);
		recordAdd.add(recordIC);
		rIN = new JLabel("Item Name");
		rIN.setBounds(15,70,100,20);
		recordAdd.add(rIN);
		recordIN = new JTextField();
		recordIN.setBounds(15,95,150,25);
		recordAdd.add(recordIN);
		rQ = new JLabel("Quantity");
		rQ.setBounds(220,10,100,20);
		recordAdd.add(rQ);
		recordQ = new JTextField();
		recordQ.setBounds(220,35,150,25);
		recordAdd.add(recordQ);
		rP = new JLabel("M.R.P.");
		rP.setBounds(220,70,100,20);
		recordAdd.add(rP);
		recordP = new JTextField();
		recordP.setBounds(220,95,150,25);
		recordAdd.add(recordP);
		rD = new JLabel("Discount");
		rD.setBounds(15,130,100,20);
		recordAdd.add(rD);
		recordD = new JTextField();
		recordD.setBounds(15,155,150,25);
		recordAdd.add(recordD);
		rDQ = new JLabel("Dis. Quantity");
		rDQ.setBounds(220,130,100,20);
		recordAdd.add(rDQ);
		recordDQ = new JTextField();
		recordDQ.setBounds(220,155,150,25);
		recordAdd.add(recordDQ);
		rmsg = new JLabel("");
		rmsg.setFont(new Font("Calibri",Font.BOLD,15));
		rmsg.setForeground(Color.RED);
		rmsg.setBounds(30,190,500,25);
		recordAdd.add(rmsg);
		rSave = new JButton("SAVE");
		rSave.setBounds(140,230,100,25);
		rSave.addActionListener(this);
		recordAdd.add(rSave);
		
		
		
		
		//UPDATE ITEM DIALOG BOX
		update = new JButton("UPDATE");
		update.setBounds(965,20,90,25);
		update.addActionListener(this);
		f4.add(update);
		recordUpdate = new JDialog();
		recordUpdate.setLayout(null);
		recordUpdate.setTitle("Update Item");
		recordUpdate.setBounds(600,160,400,420);
		recordUpdate.setModal(true);
		usIC = new JLabel("Item Code");
		usIC.setBounds(15,10,100,20);
		recordUpdate.add(usIC);
		updatesIC = new JTextField();
		updatesIC.setBounds(15,35,150,25);
		recordUpdate.add(updatesIC);
		uSearch = new JButton("SEARCH");
		uSearch.setBounds(210,35,90,23);
		uSearch.addActionListener(this);
		recordUpdate.add(uSearch);
		umsg=new JLabel("");
		umsg.setFont(new Font("Calibri",Font.BOLD,15));
		umsg.setForeground(Color.RED);
		umsg.setBounds(18,65,500,25);
		recordUpdate.add(umsg);
		uh =  new JLabel("Details:");
		uh.setBounds(20,80,200,50);
		uh.setFont(new Font("Calibri",Font.BOLD,18));
		recordUpdate.add(uh);
		uIC = new JLabel("Item Code");
		uIC.setBounds(15,120,100,20);
		recordUpdate.add(uIC);
		updateIC = new JTextField();
		updateIC.setBounds(15,145,150,25);
		recordUpdate.add(updateIC);
		uIN = new JLabel("Item Name");
		uIN.setBounds(15,180,100,20);
		recordUpdate.add(uIN);
		updateIN = new JTextField();
		updateIN.setBounds(15,205,150,25);
		recordUpdate.add(updateIN);
		uQ = new JLabel("Quantity");
		uQ.setBounds(220,120,100,20);
		recordUpdate.add(uQ);
		updateQ = new JTextField();
		updateQ.setBounds(220,145,150,25);
		recordUpdate.add(updateQ);
		uP = new JLabel("M.R.P.");
		uP.setBounds(220,180,100,20);
		recordUpdate.add(uP);
		updateP = new JTextField();
		updateP.setBounds(220,205,150,25);
		recordUpdate.add(updateP);
		uD = new JLabel("Discount");
		uD.setBounds(15,240,100,20);
		recordUpdate.add(uD);
		updateD = new JTextField();
		updateD.setBounds(15,265,150,25);
		recordUpdate.add(updateD);
		uDQ = new JLabel("Dis. Quantity");
		uDQ.setBounds(220,240,100,20);
		recordUpdate.add(uDQ);
		updateDQ = new JTextField();
		updateDQ.setBounds(220,265,150,25);
		recordUpdate.add(updateDQ);
		uupdate = new JButton("UPDATE");
		uupdate.setBounds(140,340,100,25);
		uupdate.addActionListener(this);
		recordUpdate.add(uupdate);	
		
		
		
		
		
		
		//REMOVE ITEM DIALOG BOX
		recordRemove = new JDialog();
		recordRemove.setLayout(null);
		recordRemove.setTitle("Remove Item");
		recordRemove.setBounds(600,200,400,190);
		recordRemove.setModal(true);
		rrIC = new JLabel("Item Code");
		rrIC.setBounds(120,10,100,20);
		recordRemove.add(rrIC);
		rrecordIC = new JTextField();
		rrecordIC.setBounds(120,35,150,25);
		recordRemove.add(rrecordIC);
		rrmsg = new JLabel("");
		rrmsg.setFont(new Font("Calibri",Font.BOLD,15));
		rrmsg.setForeground(Color.RED);
		rrmsg.setBounds(30,70,500,25);
		recordRemove.add(rrmsg);
		rRemove = new JButton("REMOVE");
		rRemove.setBounds(140,110,100,25);
		rRemove.addActionListener(this);
		recordRemove.add(rRemove);
		
		sremove = new JButton("REMOVE");
		sremove.setBounds(1065,20,90,25);
		sremove.addActionListener(this);
		f4.add(sremove);		
		 stTable = new JTable();
		 stTable.setEnabled(false);
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
			st = con.createStatement();
			rs = st.executeQuery("select * from stock");
		 } catch (SQLException | ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, e1);
		 }
		 stTable.setModel(DbUtils.resultSetToTableModel(rs));
		 stTable.setFont(new Font("Serif",Font.BOLD,15));
		 stTable.setRowHeight(30);
		 ((DefaultTableCellRenderer)stTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
		 sp4 = new JScrollPane(stTable);
		 sp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 sp4.setBounds(0,70,1164,524);
		 f4.add(sp4);
		show.add(f4,"Stock");
		
		
		
		
		
		//NOTIFICATION PANEL
		f5=new JPanel();
		f5.setLayout(null);
		JLabel h5 = new JLabel("NOTIFICATIONS");
		h5.setFont(new Font("Serif",Font.BOLD,25));
		h5.setBounds(500,20,250,20);
		f5.add(h5);
		nTable = new JTable();
		nTable.setEnabled(false);
		nTable.setFont(new Font("Serif",Font.BOLD,15));
		nTable.setRowHeight(30);
		((DefaultTableCellRenderer)nTable.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
		sp5 = new JScrollPane(nTable);
		sp5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp5.setBounds(0,70,1164,524);
		f5.add(sp5);
		try {
		 	Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
			st = con.createStatement();
			rs=st.executeQuery("select ItemCode,ItemName,ItemPrice,Quantity from stock where Quantity<30");
			nTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}	
		show.add(f5,"Notify");
		
		
		
		
		
		//ADMIN DETAILS
		f6=new JPanel();
		f6.setLayout(null);
		JLabel h6 = new JLabel("ADMIN DETAILS");
		h6.setFont(new Font("Serif",Font.BOLD,25));
		h6.setBounds(500,20,250,20);
		f6.add(h6);
		fnamee = new JLabel("First Name: ");
		fnamee.setBounds(50,100,100,20);
		f6.add(fnamee);
		fname = new JTextField(50);
		fname.setBounds(130,100,210,25);
		fname.setEditable(false);
		f6.add(fname);
		lnamee = new JLabel("Last Name: ");
		lnamee.setBounds(650,100,100,20);
		f6.add(lnamee);
		lname = new JTextField(50);
		lname.setBounds(730,100,210,25);
		lname.setEditable(false);
		f6.add(lname);
		mail = new JLabel("Email: ");
		mail.setBounds(50,150,100,20);
		f6.add(mail);
		email = new JTextField(50);
		email.setBounds(130,150,310,25);
		email.setEditable(false);
		f6.add(email);
		JLabel l1 = new JLabel("Password Change");
		l1.setFont(new Font("Calibri",Font.BOLD,20));
		l1.setBounds(50,220,310,25);
		f6.add(l1);
		JLabel l2 = new JLabel("Current password (leave blank to leave unchanged)");
		l2.setBounds(50,260,310,25);
		f6.add(l2);
		opswd = new JTextField(50);
		opswd.setBounds(50,290,310,25);
		f6.add(opswd);
		JLabel l3 = new JLabel("New password (leave blank to leave unchanged)");
		l3.setBounds(50,330,300,25);
		f6.add(l3);
		npswd = new JTextField(50);
		npswd.setBounds(50,360,310,25);
		f6.add(npswd);
		JLabel l4 = new JLabel("Confirm new password");
		l4.setBounds(50,400,310,25);
		f6.add(l4);
		cpswd = new JTextField(50);
		cpswd.setBounds(50,430,310,25);
		f6.add(cpswd);
		pchange = new JButton("SAVE CHANGES");
		pchange.setBounds(500,500,130,26);
		pchange.addActionListener(this);
		f6.add(pchange);
		show.add(f6,"Admin");
		
		
		
		
		

		//HEADING
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setSize(1366,110);
		header.setBorder(BorderFactory.createLoweredBevelBorder());
		URL url=Login.class.getResource("resources/icon2.png");
		ImageIcon ico = new ImageIcon(url);
		JLabel icon = new JLabel(ico);
		icon.setBounds(10,5, 100, 100);
		JLabel heading = new JLabel("Billing Management");
		heading.setForeground(Color.GRAY);
		heading.setFont(new Font("Serif",Font.BOLD,80));
		heading.setBounds(120, 5, 870,100);
		JLabel info1 = new JLabel("");
		
		info1.setFont(new Font("Times New Roman",Font.BOLD,17));
		JLabel info2 = new JLabel("+91-8439481236");
		info2.setFont(new Font("Times New Roman",Font.BOLD,17));
		info1.setBounds(1230, 65, 130, 15);
		info2.setBounds(1220, 88, 150, 15);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
			st = con.createStatement();
			ResultSet r = st.executeQuery("select * from admin where UserId='"+id+"'");
			if(r.next())
				info1.setText(r.getString(3)+" "+r.getString(4));
				info2.setText("+91-"+r.getString(6));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		header.add(icon);
		header.add(heading);
		header.add(info1);
		header.add(info2);
		add(header,BorderLayout.NORTH);
		
		
		
		
		
		//COMMON PANEL
		show.setSize(200, 200);
		show.setBounds(200	, 110, 1166, 595);
		show.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(show);
		
		billing.addActionListener(this);
		sales.addActionListener(this);
		stock.addActionListener(this);
		notify.addActionListener(this);
		admin.addActionListener(this);
		logout.addActionListener(this);
		exit.addActionListener(this);
		setVisible(true);
				
	}
	
	
	
	
	
	//PRINTING...
	public static class btnPrintAction implements ActionListener,Printable {
		
		@Override
		public int print(Graphics gx, PageFormat pf, int page) throws PrinterException {
			ResultSet rs;
			Connection con=null;
			Statement st = null;
			int y=190;
			Date d = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyy  HH:mm:ss");
			Graphics2D g = (Graphics2D)gx;
			g.translate(pf.getImageableX(),pf.getImageableY());
			if(page>0)
				return NO_SUCH_PAGE;
			else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
					st = con.createStatement();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				try {
					g.drawString("############ Billing Management ############",170,50);
					g.drawString("-------------------------------------------------------------------------------------------------------",100,90);
					g.drawString("INVOICE",280,102);
					g.drawString("-------------------------------------------------------------------------------------------------------",100,110);
					g.drawString("Date: "+ft.format(d),100,136);
					g.drawString("-------------------------------------------------------------------------------------------------------",100,152);
					g.drawString("CODE",100,165);g.drawString("NAME",170,165); 
					g.drawString("QTY",320,165); g.drawString("M.R.P.",390,165); g.drawString("PRICE",460,165);
					g.drawString("-------------------------------------------------------------------------------------------------------",100,175);
					rs = st.executeQuery("select bill.ItemCode,bill.ItemName,bill.Quantity,stock.ItemPrice,bill.Price from bill,stock where bill.ItemCode=stock.ItemCode");
					while(rs.next()) {
						g.drawString(rs.getInt(1)+"",100,y);g.drawString(rs.getString(2),170,y);g.drawString(rs.getInt(3)+"",320,y);
						g.drawString(rs.getFloat(4)+"",390,y);g.drawString(rs.getFloat(5)+"",460,y);
						y+=25;
					}
				rs= st.executeQuery("select SUM(Price),COUNT(ItemCode) from bill");
					if(rs.next()) {
						float sum = rs.getFloat(1);
						int qt = rs.getInt(2);
						g.drawString("-------------------------------------------------------------------------------------------------------",100,y);
						g.drawString("Total Price: "+sum,390,y+12);		g.drawString("Total Items: "+qt,252,y+12);
						g.drawString("-------------------------------------------------------------------------------------------------------",100,y+20);
						g.drawString("THANKS FOR SHOPPING.", 240,y+50);
						g.drawString("HAVE A NICE DAY.", 260, y+75);
						
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				return PAGE_EXISTS;
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
			if(job.printDialog()==true) {
				try {
					job.print();
				}catch(PrinterException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
			
		}
		
	} 
	

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ResultSet rsq = null;
		int rsu;
		Connection con = null;
		Statement st = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
		st = con.createStatement();
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		}
		
		
		if(e.getSource()==rSave) {
			try {
				if(Integer.parseInt(recordQ.getText())<0|Integer.parseInt(recordDQ.getText())<=0) {
					JOptionPane.showMessageDialog(null,"Quantity cannot be less than 0 AND \n Dis. Quantity cannot be less than 1");
				}
				else if(recordIC.getText().equals("")|recordIN.getText().equals("")|recordP.getText().equals("")|recordQ.getText().equals("")|recordD.getText().equals("")|recordDQ.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fields cannot be empty!");
				}
				else if(Float.parseFloat(recordP.getText())<=0|Float.parseFloat(recordD.getText())<0) {
					JOptionPane.showMessageDialog(null,"Price cannot be less than 0");
				}
				else if(Integer.parseInt(recordIC.getText())<=0) {
					JOptionPane.showMessageDialog(null,"ItemCode is not valid!");
				}
				else {
					rsu=st.executeUpdate("insert into stock (ItemCode,ItemName,ItemPrice,Quantity,Discount,DisQuantity) values(" + recordIC.getText() + ",'" + recordIN.getText() + "'," + recordP.getText() + "," + recordQ.getText() + "," + recordD.getText() + "," + recordDQ.getText() + ")");
					JOptionPane.showMessageDialog(null,"Item Added");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null,ex.getMessage());
			}
			try {
				rsq=st.executeQuery("select * from stock");
				stTable.setModel(DbUtils.resultSetToTableModel(rsq));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			recordIC.setText("");
			recordIN.setText("");
			recordP.setText("");
			recordQ.setText("");
			recordD.setText("");
			recordDQ.setText("");
		}
		
		
		if(e.getSource()==rRemove) {
			try {
				rsq=st.executeQuery("select Quantity from stock where ItemCode=" + rrecordIC.getText());
				if(rsq.next()) {
						st.executeUpdate("delete from stock where ItemCode=" + rrecordIC.getText());
						JOptionPane.showMessageDialog(null,"Item Removed");
				}
				else {
					rrmsg.setText("* Enter correct Item Code.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null,ex.getMessage());
			}
			try {
				rsq=st.executeQuery("select * from stock");
				stTable.setModel(DbUtils.resultSetToTableModel(rsq));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			recordIC.setText("");
			recordQ.setText("");
		}
		
		
		if(e.getActionCommand()=="BILLING") {
			setTitle("Billing Management Software- BILLING");
			try {
				rsq=st.executeQuery("select bill.ItemCode,bill.ItemName,bill.Quantity,stock.ItemPrice,bill.Price from bill,stock where bill.ItemCode=stock.ItemCode");
					bTable.setModel(DbUtils.resultSetToTableModel(rsq));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			cl.show(show,"Bill");
		}
		
		
		else if(e.getActionCommand()=="SALES") {
			setTitle("Billing Management Software- SALES");
			try {
				rsq=st.executeQuery("select * from sale ORDER BY TotalPrice DESC");
				slTable.setModel(DbUtils.resultSetToTableModel(rsq));
				rsq=st.executeQuery("select SUM(TotalPrice) from sale");
				if(rsq.next())
					famt.setText(rsq.getFloat(1)+"");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			cl.show(show,"Sales");
		}
		
		
		else if(e.getActionCommand()=="STOCK DETAILS") {
			setTitle("Billing Management Software- STOCK DETAILS");
			try {
				rsq=st.executeQuery("select * from stock");
				stTable.setModel(DbUtils.resultSetToTableModel(rsq));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			cl.show(show,"Stock");
		}
	
		
		else if(e.getSource()==notify) {
			setTitle("Billing Management Software- NOTIFICATION");
			try {
				rsq=st.executeQuery("select ItemCode,ItemName,ItemPrice,Quantity from stock where Quantity<=30");
				nTable.setModel(DbUtils.resultSetToTableModel(rsq));
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			cl.show(show,"Notify");
		}
		
		
		else if(e.getActionCommand()=="ADMIN") {
			setTitle("Billing Management Software- ADMIN");
			cl.show(show,"Admin");
			try {
				rsq=st.executeQuery("select * from admin where UserId= '"+idd+"'");
				if(rsq.next())
				fname.setText(rsq.getString(3));
				lname.setText(rsq.getString(4));
				email.setText(rsq.getString(5));
				opswd.setText("");
				npswd.setText("(4-10) characters");
				cpswd.setText("");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		
		else if(e.getActionCommand()=="LOGOUT") {
			this.setVisible(false);
			new Login().setVisible(true);
		}
		else if(e.getActionCommand()=="EXIT") {
			System.exit(0);
		}
		
		
		if(e.getSource()==sadd) {
			recordAdd.setVisible(true);
		}
		
		
		if(e.getSource()==sremove) {
			recordRemove.setVisible(true);
		}
		
		
		if(e.getSource()==update) {
			recordUpdate.setVisible(true);
		}
		
		
		if(e.getSource()==addd) {
			if(itemCode.getText().equals("")|quantity.getText().equals(""))
				JOptionPane.showMessageDialog(null,"Fields must be filled!");
			else if(Integer.parseInt(quantity.getText())<1|Integer.parseInt(itemCode.getText())<1)
				JOptionPane.showMessageDialog(null,"Enter valid values!");
			else {
				try {
					rsq=st.executeQuery("select * from stock where ItemCode="+itemCode.getText());
					if(rsq.next()) {
						String s = rsq.getString(2);
						float p=0;
						float np = rsq.getFloat(3);
						float nd = rsq.getFloat(5);
						int q = rsq.getInt(6);
						if(rsq.getInt(4)>=Integer.parseInt(quantity.getText())) {
							ResultSet r=null;
							r=st.executeQuery("select * from bill where ItemCode="+itemCode.getText());
							if(r.next()) {
								if((r.getInt(3) + Integer.parseInt(quantity.getText()))>=q)
									p=((r.getInt(3)+Integer.parseInt(quantity.getText())) * np)-((r.getInt(3) +Integer.parseInt(quantity.getText())) * nd);
								else
									p=(r.getInt(3)+Integer.parseInt(quantity.getText())) * np;
								st.executeUpdate("update bill set Quantity= Quantity+"+quantity.getText()+",Price="+p+"where ItemCode="+itemCode.getText());
							}
							else {
								if((Integer.parseInt(quantity.getText()))>=q)
									p=(Integer.parseInt(quantity.getText()) * np)-(Integer.parseInt(quantity.getText()) * nd);
								else
									p=Integer.parseInt(quantity.getText()) * np;
								st.executeUpdate("insert into bill values("+itemCode.getText()+",'"+ s +"',"+quantity.getText()+","+p+")");
							}
							rsq=st.executeQuery("select bill.ItemCode,bill.ItemName,bill.Quantity,stock.ItemPrice,bill.Price from bill,stock where bill.ItemCode=stock.ItemCode");
							bTable.setModel(DbUtils.resultSetToTableModel(rsq));
							st.executeUpdate("update stock set Quantity=Quantity-"+quantity.getText()+" where ItemCode="+itemCode.getText());
							quantity.setText("");
							itemCode.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null,"Available quantity is: "+rsq.getInt(4));
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Invalid Item Code!");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
		}
		
		
		if(e.getSource()==remove) {		
			if(itemCode.getText().equals("") & quantity.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Field must be filled!");
			}
			else if(Integer.parseInt(quantity.getText())<1|Integer.parseInt(itemCode.getText())<1) {
				JOptionPane.showMessageDialog(null,"Enter valid values!");
			}
			else if(!itemCode.getText().equals("") & !quantity.getText().equals("")){
				try {
					rsq=st.executeQuery("select * from bill where ItemCode="+itemCode.getText());
					if(rsq.next()) {
						int q=rsq.getInt(3);
						float p=0;
						if(q>Integer.parseInt(quantity.getText())) {
							ResultSet r=st.executeQuery("select * from stock where ItemCode="+itemCode.getText());
							if(r.next()) {
							if(q-Integer.parseInt(quantity.getText())>=r.getInt(6))
								p=((q-Integer.parseInt(quantity.getText())) * r.getInt(3))-((q-Integer.parseInt(quantity.getText())) * r.getInt(5));
							else
								p=(q-Integer.parseInt(quantity.getText())) * r.getInt(3);
							st.executeUpdate("update stock,bill set stock.Quantity=stock.Quantity+"+quantity.getText() + " where stock.ItemCode= "+itemCode.getText());
							st.executeUpdate("update bill set Quantity=Quantity-"+quantity.getText() +",Price="+p+" where ItemCode="+itemCode.getText());
							JOptionPane.showMessageDialog(null,"Item Altered!");
							rsq=st.executeQuery("select bill.ItemCode,bill.ItemName,bill.Quantity,stock.ItemPrice,bill.Price from bill,stock where bill.ItemCode=stock.ItemCode");
							bTable.setModel(DbUtils.resultSetToTableModel(rsq));
							quantity.setText("");
							itemCode.setText("");}
						}
						else if(q==Integer.parseInt(quantity.getText())) {
							st.executeUpdate("update stock,bill set stock.Quantity=stock.Quantity+bill.Quantity where stock.ItemCode= "+itemCode.getText());
							st.executeUpdate("delete from bill where ItemCode="+itemCode.getText());
							JOptionPane.showMessageDialog(null,"Item removed!");
							rsq=st.executeQuery("select * from bill");
							bTable.setModel(DbUtils.resultSetToTableModel(rsq));
							quantity.setText("");
							itemCode.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null,"Invalid Quantity!");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Item not found!");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
			
		}
		
		
		if(e.getSource()==done) {
			try {
				rsq=st.executeQuery("select * from bill");
				if(rsq.next()) {
					rsq=st.executeQuery("select SUM(Price),COUNT(ItemCode) from bill");
					if(rsq.next()) {
						tPrice.setText(rsq.getFloat(1)+"");
						tQty.setText(rsq.getInt(2)+"");
						cl.show(show,"Done");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"BILL IS EMPTY");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		
		
		if(e.getSource()==back) {
			cl.show(show,"Bill");
		}
		
		
		if(e.getSource()==cnfm) {
			try {
				rsq=st.executeQuery("select SUM(Price),COUNT(ItemCode) from bill");
				if(rsq.next()) {
					tPrice.setText(rsq.getFloat(1)+"");
					tQty.setText(rsq.getInt(2)+"");
				}
				st.executeUpdate("update sale,bill set sale.Quantity=sale.Quantity+bill.Quantity, sale.TotalPrice=sale.TotalPrice+bill.Price where sale.ItemCode=bill.ItemCode");
				st.executeUpdate("INSERT INTO sale select * FROM bill as t1 WHERE NOT EXISTS(SELECT * FROM sale AS t2 WHERE t1.ItemCode = t2.ItemCode)");	
				st.executeUpdate("truncate bill");
				rsq=st.executeQuery("select * from bill");
				bTable.setModel(DbUtils.resultSetToTableModel(rsq));
				JOptionPane.showMessageDialog(null,"THANKS FOR SHOPING...");
				cl.show(show,"Bill");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		}
		
		
		if(e.getSource()==empty) {
			try {
				st.executeUpdate("update stock,bill set stock.Quantity=stock.Quantity+bill.Quantity where stock.ItemCode=bill.ItemCode");
				st.executeUpdate("truncate table bill");
				JOptionPane.showMessageDialog(null,"DONE.");
				rsq=st.executeQuery("select * from bill");
				bTable.setModel(DbUtils.resultSetToTableModel(rsq));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		
		if(e.getSource()==pchange) {
			if(npswd.getText().equals("")|cpswd.getText().equals("")|opswd.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"All fields must be filled.");
			}
			else if(npswd.getText().length()<4|npswd.getText().length()>10) {
				JOptionPane.showMessageDialog(null,"Password length violated!");
			}
			else if(npswd.getText().equals(cpswd.getText())) {
				try {
					rsq=st.executeQuery("select * from admin where Email= '"+email.getText()+"' AND Password= '"+opswd.getText()+"'");
					if(rsq.next()) {
						st.executeUpdate("UPDATE admin SET Password='"+npswd.getText()+"' where Email= '"+email.getText()+"'");
						JOptionPane.showMessageDialog(null,"Password changed.");
						opswd.setText("");
						npswd.setText("(4-10) characters");
						cpswd.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null,"Current password not matched.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
			else {
				JOptionPane.showMessageDialog(null,"New password and Confirm password NOT MATCHED.");
			}
		}
		
		
		if(e.getSource()==go) {
			try {
				rsq=st.executeQuery("select * from stock where ItemCode LIKE '%"+search.getText()+"%' OR ItemName LIKE '%"+search.getText()+"%'");
					stTable.setModel(DbUtils.resultSetToTableModel(rsq));
				if(rsq.next()){
					JOptionPane.showMessageDialog(null,"No data found.");
					rsq=st.executeQuery("select * from stock");
					stTable.setModel(DbUtils.resultSetToTableModel(rsq));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		
		if(e.getSource()==go1) {
			try {
				rsq=st.executeQuery("select * from sale where ItemCode LIKE '%"+search1.getText()+"%' OR ItemName LIKE '%"+search1.getText()+"%'");
					slTable.setModel(DbUtils.resultSetToTableModel(rsq));
				if(rsq.next()){
					JOptionPane.showMessageDialog(null,"No data found.");
					rsq=st.executeQuery("select * from sale");
					slTable.setModel(DbUtils.resultSetToTableModel(rsq));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		
		if(e.getSource()==uSearch) {
			try {
				rsq=st.executeQuery("select * from stock where ItemCode="+updatesIC.getText());
				if(rsq.next()){
					umsg.setText("");
					s = updatesIC.getText();
					updateIC.setText(rsq.getInt(1)+"");
					updateIN.setText(rsq.getString(2)+"");
					updateP.setText(rsq.getFloat(3)+"");
					updateQ.setText(rsq.getInt(4)+"");
					updateD.setText(rsq.getFloat(5)+"");
					updateDQ.setText(rsq.getInt(6)+"");
				}
				else {
					umsg.setText("ItemCode not matched.");
					updateIC.setText("");
					updateIN.setText("");
					updateP.setText("");
					updateQ.setText("");
					updateD.setText("");
					updateDQ.setText("");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		
		if(e.getSource()==uupdate) {
			try {
				if(Integer.parseInt(updateQ.getText())<0|Integer.parseInt(updateDQ.getText())<=0) {
					JOptionPane.showMessageDialog(null,"Quantity cannot be less than 0 AND \n Dis. Quantity cannot be less than 1");
				}
				else if(updateIC.getText().equals("")|updateIN.getText().equals("")|updateP.getText().equals("")|updateQ.getText().equals("")|updateD.getText().equals("")|updateDQ.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fields cannot be empty!");
				}
				else if(Float.parseFloat(updateP.getText())<=0|Float.parseFloat(updateD.getText())<0) {
					JOptionPane.showMessageDialog(null,"Price cannot be less than 0");
				}
				else if(Integer.parseInt(updateIC.getText())<=0) {
					JOptionPane.showMessageDialog(null,"ItemCode is not valid!");
				}
				else {
					st.executeUpdate("update stock SET ItemCode="+updateIC.getText()+", ItemName='"+updateIN.getText()+"', ItemPrice="+updateP.getText()+", Quantity="+updateQ.getText()+", Discount="+updateD.getText()+", DisQuantity="+updateDQ.getText()+" where ItemCode="+s);
					JOptionPane.showMessageDialog(null,"Item Updated.");
					updateIC.setText("");
					updateIN.setText("");
					updateP.setText("");
					updateQ.setText("");
					updateD.setText("");
					updateDQ.setText("");
					updatesIC.setText("");
					rsq=st.executeQuery("select * from stock");
					stTable.setModel(DbUtils.resultSetToTableModel(rsq));
				}
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}	
		}
		
	}

}
