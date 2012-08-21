package edu.ucdenver.ccp.datasource.identifiers.transfac;

import edu.ucdenver.ccp.common.string.RegExPatterns;
import edu.ucdenver.ccp.datasource.identifiers.DataSource;
import edu.ucdenver.ccp.datasource.identifiers.StringDataSourceIdentifier;

public class TransfacGeneID extends StringDataSourceIdentifier {

	public TransfacGeneID(String resourceID) {
		super(resourceID);
	}

	@Override
	public DataSource getDataSource() {
		return DataSource.TRANSFAC;
	}

	@Override
	public String validate(String resourceID) throws IllegalArgumentException {
		resourceID = super.validate(resourceID);
		if (resourceID.startsWith("G") && resourceID.substring(1).matches(RegExPatterns.HAS_NUMBERS_ONLY))
			return resourceID;
		throw new IllegalArgumentException(String.format("Invalid Transfac Gene ID detected: %s", resourceID));

	}

}