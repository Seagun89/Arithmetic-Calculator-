package calc;
import java.awt.EventQueue;

class MyMainCalc {
/**
 * Launch the application.
 * @param args the command line arguments
 * @wbp.parser.entryPoint
*/
//Runs the calculator calls both the view and model to be parameters in the controller instance
public static void main(String args[]) {
   try {
       for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
           if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
           }//Close if
       }//Close for
   } catch (ClassNotFoundException ex) {
       java.util.logging.Logger.getLogger(MyCalcView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
   } catch (InstantiationException ex) {
       java.util.logging.Logger.getLogger(MyCalcView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
   } catch (IllegalAccessException ex) {
       java.util.logging.Logger.getLogger(MyCalcView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
   } catch (javax.swing.UnsupportedLookAndFeelException ex) {
       java.util.logging.Logger.getLogger(MyCalcView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
   }//Close catch 
   //</editor-fold>

/*Create and display the form */
   EventQueue.invokeLater(new Runnable() {
       public void run() {
         	MyCalcModel theModel = new MyCalcModel();
         	new MyCalcView(theModel);
          	
       }//Close run
   });//Close invokeLater
}//Close Main Method
}//Close MyMainClac Class
