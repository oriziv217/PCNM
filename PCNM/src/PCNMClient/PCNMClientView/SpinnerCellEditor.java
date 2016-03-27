package PCNMClient.PCNMClientView;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.table.TableCellEditor;

/**
 * This class defines embedded number spinner in a table cell
 * @author Ori Ziv
 */
class SpinnerCellEditor extends AbstractCellEditor implements TableCellEditor {
   protected JSpinner spinner;
   
   public SpinnerCellEditor() {
      spinner = new JSpinner();
   }
  
   @Override
   public Component getTableCellEditorComponent(JTable table, Object value,
                    boolean isSelected, int row, int column) {
      spinner.setModel((SpinnerModel) value);
  
      return spinner;
   }
  
   @Override
   public Object getCellEditorValue() {
      SpinnerModel sm = spinner.getModel();
      return sm;
   }
}