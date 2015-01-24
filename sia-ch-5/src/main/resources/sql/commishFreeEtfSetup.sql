-- remember you have the tickers and the descriptions
-- if you need to break this up into an insert ignore
-- for the tickers, and update for the descriptions
insert into stocks (ticker, description, commishFreeEtf) values
('VGSH', 'Vanguard Short', 'Y'),
('VIG', 'Vanguard Dividend Appreciation ETF Large Blend ', 'Y'),
('VMBS', 'Vanguard Mortgage', 'Y'),
('VNQ', 'Vanguard REIT Index ETF Real Estate ', 'Y'),
('VO', 'Vanguard Mid Cap ETF Mid', 'Y'),
('VOE', 'Vanguard Mid', 'Y'),
('VOT', 'Vanguard Mid', 'Y'),
('VSS', 'Vanguard FTSE All', 'Y'),
('VT', 'Vanguard Total World Stock Index', 'Y'),
('VTI', 'Vanguard Total Stock Market ETF Large Blend ', 'Y'),
('VTV', 'Vanguard Value ETF Large Value ', 'Y'),
('VUG', 'Vanguard Growth ETF Large Growth ', 'Y'),
('VWO', 'Vanguard FTSE Emerging Markets', 'Y'),
('VXF', 'Vanguard Extended Market Index', 'Y'),
('VYM', 'Vanguard High Dividend Yield Index', 'Y'),
('WIP', 'SPDR DB International Government', 'Y'),
('RSX', 'Market Vectors Russia Index ETF Europe Stock ', 'Y'),
('RWO', 'SPDR Dow Jones Global Real', 'Y'),
('RWR', 'SPDR Dow Jones REIT ETF Real Estate ', 'Y'),
('RWX', 'SPDR Dow Jones International Real', 'Y'),
('SCZ', 'iShares MSCI EAFE Small Cap', 'Y'),
('SHM', 'SPDR Nuveen Barclays Capital', 'Y'),
('SHY', 'iShares Barclays 1', 'Y'),
('STPZ', 'PIMCO 1', 'Y'),
('TFI', 'SPDR Nuveen Barclays Capital', 'Y'),
('TIP', 'iShares Barclays TIPS Bond Fund Inflation', 'Y'),
('TLT', 'iShares Barclays 20', 'Y'),
('VB', 'Vanguard Small Cap ETF Small Blend ', 'Y'),
('VBK', 'Vanguard Small Cap Growth ETF Small Growth ', 'Y'),
('VBR', 'Vanguard Small Cap Value ETF Small Value ', 'Y'),
('VCIT', 'Vanguard Intermediate', 'Y'),
('VCLT', 'Vanguard Long', 'Y'),
('VCSH', 'Vanguard Short', 'Y'),
('VEA', 'Vanguard Tax', 'Y'),
('VEU', 'Vanguard FTSE All', 'Y'),
('VGIT', 'Vanguard Intermediate', 'Y'),
('VGK', 'Vanguard FTSE Europe ETF Europe Stock ', 'Y'),
('VGLT', 'Vanguard Long', 'Y'),
('IJR', 'iShares Core S&P Small', 'Y'),
('IJS', 'iShares S&P SmallCap 600 Value', 'Y'),
('ILF', 'iShares S&P Latin America 40 Index', 'Y'),
('IOO', 'iShares S&P Global 100 Index Fund World Stock ', 'Y'),
('ITM', 'Market Vectors Intermediate', 'Y'),
('IVE', 'iShares S&P 500 Value Index Fund Large Value ', 'Y'),
('IVV', 'iShares Core S&P 500 ETF Large Blend ', 'Y'),
('IWB', 'iShares Russell 1000 Index Fund Large Blend ', 'Y'),
('IWD', 'iShares Russell 1000 Value Index', 'Y'),
('IWF', 'iShares Russell 1000 Growth Index', 'Y'),
('IWN', 'iShares Russell 2000 Value Index', 'Y'),
('IWO', 'iShares Russell 2000 Growth Index', 'Y'),
('IWP', 'iShares Russell Midcap Growth', 'Y'),
('IWS', 'iShares Russell Midcap Value Index', 'Y'),
('IWV', 'iShares Russell 3000 Index Fund Large Blend ', 'Y'),
('JNK', 'SPDR Barclays High Yield Bond ETF High Yield Bond ', 'Y'),
('LQD', 'iShares iBoxx $ Investment Grade', 'Y'),
('MBB', 'iShares Barclays MBS Bond Fund Intermediate', 'Y'),
('MGK', 'Vanguard Mega Cap Growth Index', 'Y'),
('MUB', 'iShares S&P National AMT', 'Y'),
('OEF', 'iShares S&P 100 Index Fund Large Blend ', 'Y'),
('PCY', 'PowerShares Emerging Markets', 'Y'),
('EDV', 'Vanguard Extended Duration', 'Y'),
('EFG', 'iShares MSCI EAFE Growth Index', 'Y'),
('EFV', 'iShares MSCI EAFE Value Index', 'Y'),
('EMLC', 'Market Vectors Emerging Markets', 'Y'),
('EPI', 'WisdomTree India Earnings Fund India Equity ', 'Y'),
('EWA', 'iShares MSCI Australia Index Fund Pacific/Asia ex', 'Y'),
('EWC', 'iShares MSCI Canada Index Fund Foreign Large Value ', 'Y'),
('EWG', 'iShares MSCI Germany Index Fund Europe Stock ', 'Y'),
('EWI', 'iShares MSCI Italy Capped Index', 'Y'),
('EWJ', 'iShares MSCI Japan Index Fund Japan Stock ', 'Y'),
('EWP', 'iShares MSCI Spain Capped Index', 'Y'),
('EWQ', 'iShares MSCI France Index Fund Europe Stock ', 'Y'),
('EWU', 'iShares MSCI United Kingdom Index', 'Y'),
('EWX', 'SPDR S&P Emerging Markets Small', 'Y'),
('EWZ', 'iShares MSCI Brazil Capped Index', 'Y'),
('FEZ', 'SPDR EURO STOXX 50 Europe Stock ', 'Y'),
('FXI', 'iShares FTSE China 25 Index Fund China Region ', 'Y'),
('GUR', 'SPDR S&P Emerging Europe ETF Diversified Emerging Mkts ', 'Y'),
('GVI', 'iShares Barclays Intermediate', 'Y'),
('GXC', 'SPDR S&P China ETF China Region ', 'Y'),
('IEI', 'iShares Barclays 3', 'Y'),
('IJH', 'iShares Core S&P Mid', 'Y'),
('AAXJ', 'iShares MSCI All Country Asia', 'Y'),
('ex', 'ACWI iShares MSCI ACWI Index Fund World Stock ', 'Y'),
('AGG', 'iShares Core Total U.S. Bond', 'Y'),
('AOA', 'iShares S&P Aggressive Allocation', 'Y'),
('AOK', 'iShares S&P Conservative', 'Y'),
('AOM', 'iShares S&P Moderate Allocation', 'Y'),
('AOR', 'iShares S&P Growth Allocation Fund Moderate Allocation ', 'Y'),
('BIV', 'Vanguard Intermediate', 'Y'),
('BKF', 'iShares MSCI BRIC Index Fund Diversified Emerging Mkts ', 'Y'),
('BLV', 'Vanguard Long', 'Y'),
('BND', 'Vanguard Total Bond Market ETF Intermediate', 'Y'),
('BSV', 'Vanguard Short', 'Y'),
('BWX', 'SPDR Barclays International', 'Y'),
('CIU', 'iShares Barclays Intermediate', 'Y'),
('CSJ', 'iShares Barclays 1', 'Y'),
('DBC', 'PowerShares DB Commodity Index', 'Y'),
('DBO', 'PowerShares DB Oil Trust Commodities Energy ', 'Y'),
('DBV', 'PowerShares DB G10 Currency', 'Y'),
('DJP', 'iPath Dow Jones', 'Y');
