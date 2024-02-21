package com.ferraro.uni.enums;

public enum Provincia {
	
	AG("Agrigento (AG)"),
	AL("Alessandria (AL)"),
	AN("Ancona (AN)"),
	AO("Aosta (AO)"),
	AQ("L'Aquila (AQ)"),
	AR("Arezzo (AR)"),
	AP("Ascoli Piceno (AP)"),
	AT("Asti (AT)"),
	AV("Avellino (AV)"),
	BA("Bari (BA)"),
	BT("Barletta-Andria-Trani (BT)"),
	BL("Belluno (BL)"),
	BN("Benevento (BN)"),
	BG("Bergamo (BG)"),
	BI("Biella (BI)"),
	BO("Bologna (BO)"),
	BZ("Bolzano (BZ)"),
	BS("Brescia (BS)"),
	BR("Brindisi (BR)"),
	CA("Cagliari (CA)"),
	CL("Caltanissetta (CL)"),
	CB("Campobasso (CB)"),
	CI("Carbonia-Iglesias (CI)"),
	CE("Caserta (CE)"),
	CT("Catania (CT)"),
	CZ("Catanzaro (CZ)"),
	CH("Chieti (CH)"),
	CO("Como (CO)"),
	CS("Cosenza (CS)"),
	CR("Cremona (CR)"),
	KR("Crotone (KR)"),
	CN("Cuneo (CN)"),
	EN("Enna (EN)"),
	FM("Fermo (FM)"),
	FE("Ferrara (FE)"),
	FI("Firenze (FI)"),
	FG("Foggia (FG)"),
	FC("Forlì-Cesena (FC)"),
	FR("Frosinone (FR)"),
	GE("Genova (GE)"),
	GO("Gorizia (GO)"),
	GR("Grosseto (GR)"),
	IM("Imperia (IM)"),
	IS("Isernia (IS)"),
	SP("La Spezia (SP)"),
	LT("Latina (LT)"),
	LE("Lecce (LE)"),
	LC("Lecco (LC)"),
	LI("Livorno (LI)"),
	LO("Lodi (LO)"),
	LU("Lucca (LU)"),
	MC("Macerata (MC)"),
	MN("Mantova (MN)"),
	MS("Massa-Carrara (MS)"),
	MT("Matera (MT)"),
	VS("Medio Campidano (VS)"),
	ME("Messina (ME)"),
	MI("Milano (MI)"),
	MO("Modena (MO)"),
	MB("Monza e della Brianza (MB)"),
	NA("Napoli (NA)"),
	NO("Novara (NO)"),
	NU("Nuoro (NU)"),
	OG("Ogliastra (OG)"),
	OT("Olbia-Tempio (OT)"),
	OR("Oristano (OR)"),
	PD("Padova (PD)"),
	PA("Palermo (PA)"),
	PR("Parma (PR)"),
	PV("Pavia (PV)"),
	PG("Perugia (PG)"),
	PU("Pesaro e Urbino (PU)"),
	PE("Pescara (PE)"),
	PC("Piacenza (PC)"),
	PI("Pisa (PI)"),
	PT("Pistoia (PT)"),
	PN("Pordenone (PN)"),
	PZ("Potenza (PZ)"),
	PO("Prato (PO)"),
	RG("Ragusa (RG)"),
	RA("Ravenna (RA)"),
	RC("Reggio Calabria (RC)"),
	RE("Reggio Emilia (RE)"),
	RI("Rieti (RI)"),
	RN("Rimini (RN)"),
	RM("Roma (RM)"),
	RO("Rovigo (RO)"),
	SA("Salerno (SA)"),
	SS("Sassari (SS)"),
	SV("Savona (SV)"),
	SI("Siena (SI)"),
	SR("Siracusa (SR)"),
	SO("Sondrio (SO)"),
	TA("Taranto (TA)"),
	TE("Teramo (TE)"),
	TR("Terni (TR)"),
	TO("Torino (TO)"),
	TP("Trapani (TP)"),
	TN("Trento (TN)"),
	TV("Treviso (TV)"),
	TS("Trieste (TS)"),
	UD("Udine (UD)"),
	VA("Varese (VA)"),
	VE("Venezia (VE)"),
	VB("Verbano-Cusio-Ossola (VB)"),
	VC("Vercelli (VC)"),
	VR("Verona (VR)"),
	VV("Vibo Valentia (VV)"),
	VI("Vicenza (VI)"),
	VT("Viterbo (VT)");

	
	  private final String fullName;

	    Provincia(String fullName) {
	        this.fullName = fullName;
	    }

	    public String getFullName() {
	        return fullName;
	    }
}
