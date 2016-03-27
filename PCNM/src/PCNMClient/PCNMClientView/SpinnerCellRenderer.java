package PCNMClient.PCNMClientView;

import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ori Ziv
 */
class SpinnerCellRenderer extends JSpinner implements TableCellRenderer {
   public SpinnerCellRenderer() {
      setOpaque(true);
   }
   
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
      setModel((SpinnerModel) value);
   
      return this;
   }
}