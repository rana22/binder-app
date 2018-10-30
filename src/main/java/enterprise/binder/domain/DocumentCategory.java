/**
 * 
 */
package enterprise.binder.domain;

/**
 * @author ambarrana
 *
 */
public class DocumentCategory {
	
	enum Insurance{
		AUTO_INSURANCE,
		HOUSE_INSURANCE,
		HEALTH_INSURANCE,
		DENTAL_INSURANCE,
		VISION_INSURANCE
	}
	
	enum Personal{
		DRIVER_LISCENCE
	}
	
	enum Bills{
		ELECTRIC_BILL,
		WATER_BILL,
		INTERNATE_BILL
	}
	
	enum Education{
		UNDER_GRAD,
		GRAD,
		HIGH_SCHOOL,
		SCHOOL
	}
	
}
