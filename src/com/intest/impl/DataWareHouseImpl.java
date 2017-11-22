package com.intest.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.intest.bean.DataWareHouse;
import com.intest.bean.DataWareHouseV1;
import com.intest.common.AppIncomSession;
import com.intest.dao.DataWareHouseDao;
import com.intest.util.BuildSeqId;

public class DataWareHouseImpl implements DataWareHouseDao {

	
	@Override
	public Boolean save(DataWareHouse dataWareHouse) {
		Session session = AppIncomSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(dataWareHouse);
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public Boolean saveOrUpdate(List<DataWareHouse> dataWareHouses) {
		if (dataWareHouses.size() > 0) {
			Session session = AppIncomSession.getSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			for (DataWareHouse dataWareHouse : dataWareHouses) {
				session.saveOrUpdate(dataWareHouse);
			}
			transaction.commit();
			session.close();
			return true;
		} else {
			return false;
		}
		
	}

	
	public static void main(String[] args) {
		DataWareHouseV1 dataWareHouse = new DataWareHouseV1();
		List<DataWareHouseV1> dataWareHouses = new ArrayList<DataWareHouseV1>();
		List<Double> list = new ArrayList<Double>();
		for(int i = 0;i < 40000; i++) {
			dataWareHouse.setSeqId(BuildSeqId.getSeqId(new Date()));
			dataWareHouse.setMd5("1F15AB7B9BCBEA5D54883863A2FFAD8C");
			dataWareHouse.setTc(201704221);
			dataWareHouse.setTe(new Timestamp(new Date().getTime()));
			dataWareHouse.setLongitude(138.9);
			dataWareHouse.setAltitude(87.8);
			dataWareHouse.setLatitude(29.3);
			dataWareHouse.setSpeed(67.9);
			dataWareHouse.setMileage(3344.9);
			dataWareHouse.setDirection(1.0);
			dataWareHouse.setVeliData(list);			
			dataWareHouses.add(dataWareHouse);
		}
		System.out.println(Deduplication(dataWareHouses).size());
		new DataWareHouseImpl().saveWithJDBC(dataWareHouses);
	}

	@Override
	public Boolean saveWithJDBC(List<DataWareHouseV1> dataWareHouseV1s) {
		String sql = "INSERT INTO DAT_DATAWAREHOUSE(SEQID, TERMINALCODE, TRAVELTIME, MD5CODE, LONGITUDE, LATITUDE, ALTITUDE, SPEED, MILEAGE, DIRECTION, DATA001, DATA002, DATA003, DATA004, DATA005, DATA006, DATA007, DATA008, DATA009, DATA010, DATA011, DATA012, DATA013, DATA014, DATA015, DATA016, DATA017, DATA018, DATA019, DATA020, DATA021, DATA022, DATA023, DATA024, DATA025, DATA026, DATA027, DATA028, DATA029, DATA030, DATA031, DATA032, DATA033, DATA034, DATA035, DATA036, DATA037, DATA038, DATA039, DATA040, DATA041, DATA042, DATA043, DATA044, DATA045, DATA046, DATA047, DATA048, DATA049, DATA050, DATA051, DATA052, DATA053, DATA054, DATA055, DATA056, DATA057, DATA058, DATA059, DATA060, DATA061, DATA062, DATA063, DATA064, DATA065, DATA066, DATA067, DATA068, DATA069, DATA070, DATA071, DATA072, DATA073, DATA074, DATA075, DATA076, DATA077, DATA078, DATA079, DATA080, DATA081, DATA082, DATA083, DATA084, DATA085, DATA086, DATA087, DATA088, DATA089, DATA090, DATA091, DATA092, DATA093, DATA094, DATA095, DATA096, DATA097, DATA098, DATA099, DATA100, DATA101, DATA102, DATA103, DATA104, DATA105, DATA106, DATA107, DATA108, DATA109, DATA110, DATA111, DATA112, DATA113, DATA114, DATA115, DATA116, DATA117, DATA118, DATA119, DATA120, DATA121, DATA122, DATA123, DATA124, DATA125, DATA126, DATA127, DATA128, DATA129, DATA130, DATA131, DATA132, DATA133, DATA134, DATA135, DATA136, DATA137, DATA138, DATA139, DATA140, DATA141, DATA142, DATA143, DATA144, DATA145, DATA146, DATA147, DATA148, DATA149, DATA150, DATA151, DATA152, DATA153, DATA154, DATA155, DATA156, DATA157, DATA158, DATA159, DATA160, DATA161, DATA162, DATA163, DATA164, DATA165, DATA166, DATA167, DATA168, DATA169, DATA170, DATA171, DATA172, DATA173, DATA174, DATA175, DATA176, DATA177, DATA178, DATA179, DATA180, DATA181, DATA182, DATA183, DATA184, DATA185, DATA186, DATA187, DATA188, DATA189, DATA190, DATA191, DATA192, DATA193, DATA194, DATA195, DATA196, DATA197, DATA198, DATA199, DATA200, DATA201, DATA202, DATA203, DATA204, DATA205, DATA206, DATA207, DATA208, DATA209, DATA210, DATA211, DATA212, DATA213, DATA214, DATA215, DATA216, DATA217, DATA218, DATA219, DATA220, DATA221, DATA222, DATA223, DATA224, DATA225, DATA226, DATA227, DATA228, DATA229, DATA230, DATA231, DATA232, DATA233, DATA234, DATA235, DATA236, DATA237, DATA238, DATA239, DATA240, DATA241, DATA242, DATA243, DATA244, DATA245, DATA246, DATA247, DATA248, DATA249, DATA250, DATA251, DATA252, DATA253, DATA254, DATA255, DATA256, DATA257, DATA258, DATA259, DATA260, DATA261, DATA262, DATA263, DATA264, DATA265, DATA266, DATA267, DATA268, DATA269, DATA270, DATA271, DATA272, DATA273, DATA274, DATA275, DATA276, DATA277, DATA278, DATA279, DATA280, DATA281, DATA282, DATA283, DATA284, DATA285, DATA286, DATA287, DATA288, DATA289, DATA290, DATA291, DATA292, DATA293, DATA294, DATA295, DATA296, DATA297, DATA298, DATA299, DATA300, DATA301, DATA302, DATA303, DATA304, DATA305, DATA306, DATA307, DATA308, DATA309, DATA310, DATA311, DATA312, DATA313, DATA314, DATA315, DATA316, DATA317, DATA318, DATA319, DATA320, DATA321, DATA322, DATA323, DATA324, DATA325, DATA326, DATA327, DATA328, DATA329, DATA330, DATA331, DATA332, DATA333, DATA334, DATA335, DATA336, DATA337, DATA338, DATA339, DATA340, DATA341, DATA342, DATA343, DATA344, DATA345, DATA346, DATA347, DATA348, DATA349, DATA350, DATA351, DATA352, DATA353, DATA354, DATA355, DATA356, DATA357, DATA358, DATA359, DATA360, DATA361, DATA362, DATA363, DATA364, DATA365, DATA366, DATA367, DATA368, DATA369, DATA370, DATA371, DATA372, DATA373, DATA374, DATA375, DATA376, DATA377, DATA378, DATA379, DATA380, DATA381, DATA382, DATA383, DATA384, DATA385, DATA386, DATA387, DATA388, DATA389, DATA390, DATA391, DATA392, DATA393, DATA394, DATA395, DATA396, DATA397, DATA398, DATA399, DATA400, DATA401, DATA402, DATA403, DATA404, DATA405, DATA406, DATA407, DATA408, DATA409, DATA410, DATA411, DATA412, DATA413, DATA414, DATA415, DATA416, DATA417, DATA418, DATA419, DATA420, DATA421, DATA422, DATA423, DATA424, DATA425, DATA426, DATA427, DATA428, DATA429, DATA430, DATA431, DATA432, DATA433, DATA434, DATA435, DATA436, DATA437, DATA438, DATA439, DATA440, DATA441, DATA442, DATA443, DATA444, DATA445, DATA446, DATA447, DATA448, DATA449, DATA450, DATA451, DATA452, DATA453, DATA454, DATA455, DATA456, DATA457, DATA458, DATA459, DATA460, DATA461, DATA462, DATA463, DATA464, DATA465, DATA466, DATA467, DATA468, DATA469, DATA470, DATA471, DATA472, DATA473, DATA474, DATA475, DATA476, DATA477, DATA478, DATA479, DATA480, DATA481, DATA482, DATA483, DATA484, DATA485, DATA486, DATA487, DATA488, DATA489, DATA490, DATA491, DATA492, DATA493, DATA494, DATA495, DATA496, DATA497, DATA498, DATA499, DATA500, DATA501, DATA502, DATA503, DATA504, DATA505, DATA506, DATA507, DATA508, DATA509, DATA510, DATA511, DATA512, DATA513, DATA514, DATA515, DATA516, DATA517, DATA518, DATA519, DATA520, DATA521, DATA522, DATA523, DATA524, DATA525, DATA526, DATA527, DATA528, DATA529, DATA530, DATA531, DATA532, DATA533, DATA534, DATA535, DATA536, DATA537, DATA538, DATA539, DATA540, DATA541, DATA542, DATA543, DATA544, DATA545, DATA546, DATA547, DATA548, DATA549, DATA550, DATA551, DATA552, DATA553, DATA554, DATA555, DATA556, DATA557, DATA558, DATA559, DATA560, DATA561, DATA562, DATA563, DATA564, DATA565, DATA566, DATA567, DATA568, DATA569, DATA570, DATA571, DATA572, DATA573, DATA574, DATA575, DATA576, DATA577, DATA578, DATA579, DATA580, DATA581, DATA582, DATA583, DATA584, DATA585, DATA586, DATA587, DATA588, DATA589, DATA590, DATA591, DATA592, DATA593, DATA594, DATA595, DATA596, DATA597, DATA598, DATA599, DATA600, DATA601, DATA602, DATA603, DATA604, DATA605, DATA606, DATA607, DATA608, DATA609, DATA610, DATA611, DATA612, DATA613, DATA614, DATA615, DATA616, DATA617, DATA618, DATA619, DATA620, DATA621, DATA622, DATA623, DATA624, DATA625, DATA626, DATA627, DATA628, DATA629, DATA630, DATA631, DATA632, DATA633, DATA634, DATA635, DATA636, DATA637, DATA638, DATA639, DATA640, DATA641, DATA642, DATA643, DATA644, DATA645, DATA646, DATA647, DATA648, DATA649, DATA650, DATA651, DATA652, DATA653, DATA654, DATA655, DATA656, DATA657, DATA658, DATA659, DATA660, DATA661, DATA662, DATA663, DATA664, DATA665, DATA666, DATA667, DATA668, DATA669, DATA670, DATA671, DATA672, DATA673, DATA674, DATA675, DATA676, DATA677, DATA678, DATA679, DATA680, DATA681, DATA682, DATA683, DATA684, DATA685, DATA686, DATA687, DATA688, DATA689, DATA690, DATA691, DATA692, DATA693, DATA694, DATA695, DATA696, DATA697, DATA698, DATA699, DATA700) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    //String sql = "MERGE INTO DAT_DATAWAREHOUSE1 T1 USING (SELECT ? SEQID FROM DUAL WHERE ROWNUM=1) T2 ON (T1.SEQID = T2.SEQID) WHEN NOT MATCHED THEN INSERT (SEQID, TERMINALCODE, TRAVELTIME, MD5CODE, LONGITUDE, LATITUDE, ALTITUDE, SPEED, MILEAGE, DIRECTION, DATA001, DATA002, DATA003, DATA004, DATA005, DATA006, DATA007, DATA008, DATA009, DATA010, DATA011, DATA012, DATA013, DATA014, DATA015, DATA016, DATA017, DATA018, DATA019, DATA020, DATA021, DATA022, DATA023, DATA024, DATA025, DATA026, DATA027, DATA028, DATA029, DATA030, DATA031, DATA032, DATA033, DATA034, DATA035, DATA036, DATA037, DATA038, DATA039, DATA040, DATA041, DATA042, DATA043, DATA044, DATA045, DATA046, DATA047, DATA048, DATA049, DATA050, DATA051, DATA052, DATA053, DATA054, DATA055, DATA056, DATA057, DATA058, DATA059, DATA060, DATA061, DATA062, DATA063, DATA064, DATA065, DATA066, DATA067, DATA068, DATA069, DATA070, DATA071, DATA072, DATA073, DATA074, DATA075, DATA076, DATA077, DATA078, DATA079, DATA080, DATA081, DATA082, DATA083, DATA084, DATA085, DATA086, DATA087, DATA088, DATA089, DATA090, DATA091, DATA092, DATA093, DATA094, DATA095, DATA096, DATA097, DATA098, DATA099, DATA100, DATA101, DATA102, DATA103, DATA104, DATA105, DATA106, DATA107, DATA108, DATA109, DATA110, DATA111, DATA112, DATA113, DATA114, DATA115, DATA116, DATA117, DATA118, DATA119, DATA120, DATA121, DATA122, DATA123, DATA124, DATA125, DATA126, DATA127, DATA128, DATA129, DATA130, DATA131, DATA132, DATA133, DATA134, DATA135, DATA136, DATA137, DATA138, DATA139, DATA140, DATA141, DATA142, DATA143, DATA144, DATA145, DATA146, DATA147, DATA148, DATA149, DATA150, DATA151, DATA152, DATA153, DATA154, DATA155, DATA156, DATA157, DATA158, DATA159, DATA160, DATA161, DATA162, DATA163, DATA164, DATA165, DATA166, DATA167, DATA168, DATA169, DATA170, DATA171, DATA172, DATA173, DATA174, DATA175, DATA176, DATA177, DATA178, DATA179, DATA180, DATA181, DATA182, DATA183, DATA184, DATA185, DATA186, DATA187, DATA188, DATA189, DATA190, DATA191, DATA192, DATA193, DATA194, DATA195, DATA196, DATA197, DATA198, DATA199, DATA200, DATA201, DATA202, DATA203, DATA204, DATA205, DATA206, DATA207, DATA208, DATA209, DATA210, DATA211, DATA212, DATA213, DATA214, DATA215, DATA216, DATA217, DATA218, DATA219, DATA220, DATA221, DATA222, DATA223, DATA224, DATA225, DATA226, DATA227, DATA228, DATA229, DATA230, DATA231, DATA232, DATA233, DATA234, DATA235, DATA236, DATA237, DATA238, DATA239, DATA240, DATA241, DATA242, DATA243, DATA244, DATA245, DATA246, DATA247, DATA248, DATA249, DATA250, DATA251, DATA252, DATA253, DATA254, DATA255, DATA256, DATA257, DATA258, DATA259, DATA260, DATA261, DATA262, DATA263, DATA264, DATA265, DATA266, DATA267, DATA268, DATA269, DATA270, DATA271, DATA272, DATA273, DATA274, DATA275, DATA276, DATA277, DATA278, DATA279, DATA280, DATA281, DATA282, DATA283, DATA284, DATA285, DATA286, DATA287, DATA288, DATA289, DATA290, DATA291, DATA292, DATA293, DATA294, DATA295, DATA296, DATA297, DATA298, DATA299, DATA300, DATA301, DATA302, DATA303, DATA304, DATA305, DATA306, DATA307, DATA308, DATA309, DATA310, DATA311, DATA312, DATA313, DATA314, DATA315, DATA316, DATA317, DATA318, DATA319, DATA320, DATA321, DATA322, DATA323, DATA324, DATA325, DATA326, DATA327, DATA328, DATA329, DATA330, DATA331, DATA332, DATA333, DATA334, DATA335, DATA336, DATA337, DATA338, DATA339, DATA340, DATA341, DATA342, DATA343, DATA344, DATA345, DATA346, DATA347, DATA348, DATA349, DATA350, DATA351, DATA352, DATA353, DATA354, DATA355, DATA356, DATA357, DATA358, DATA359, DATA360, DATA361, DATA362, DATA363, DATA364, DATA365, DATA366, DATA367, DATA368, DATA369, DATA370, DATA371, DATA372, DATA373, DATA374, DATA375, DATA376, DATA377, DATA378, DATA379, DATA380, DATA381, DATA382, DATA383, DATA384, DATA385, DATA386, DATA387, DATA388, DATA389, DATA390, DATA391, DATA392, DATA393, DATA394, DATA395, DATA396, DATA397, DATA398, DATA399, DATA400, DATA401, DATA402, DATA403, DATA404, DATA405, DATA406, DATA407, DATA408, DATA409, DATA410, DATA411, DATA412, DATA413, DATA414, DATA415, DATA416, DATA417, DATA418, DATA419, DATA420, DATA421, DATA422, DATA423, DATA424, DATA425, DATA426, DATA427, DATA428, DATA429, DATA430, DATA431, DATA432, DATA433, DATA434, DATA435, DATA436, DATA437, DATA438, DATA439, DATA440, DATA441, DATA442, DATA443, DATA444, DATA445, DATA446, DATA447, DATA448, DATA449, DATA450, DATA451, DATA452, DATA453, DATA454, DATA455, DATA456, DATA457, DATA458, DATA459, DATA460, DATA461, DATA462, DATA463, DATA464, DATA465, DATA466, DATA467, DATA468, DATA469, DATA470, DATA471, DATA472, DATA473, DATA474, DATA475, DATA476, DATA477, DATA478, DATA479, DATA480, DATA481, DATA482, DATA483, DATA484, DATA485, DATA486, DATA487, DATA488, DATA489, DATA490, DATA491, DATA492, DATA493, DATA494, DATA495, DATA496, DATA497, DATA498, DATA499, DATA500, DATA501, DATA502, DATA503, DATA504, DATA505, DATA506, DATA507, DATA508, DATA509, DATA510, DATA511, DATA512, DATA513, DATA514, DATA515, DATA516, DATA517, DATA518, DATA519, DATA520, DATA521, DATA522, DATA523, DATA524, DATA525, DATA526, DATA527, DATA528, DATA529, DATA530, DATA531, DATA532, DATA533, DATA534, DATA535, DATA536, DATA537, DATA538, DATA539, DATA540, DATA541, DATA542, DATA543, DATA544, DATA545, DATA546, DATA547, DATA548, DATA549, DATA550, DATA551, DATA552, DATA553, DATA554, DATA555, DATA556, DATA557, DATA558, DATA559, DATA560, DATA561, DATA562, DATA563, DATA564, DATA565, DATA566, DATA567, DATA568, DATA569, DATA570, DATA571, DATA572, DATA573, DATA574, DATA575, DATA576, DATA577, DATA578, DATA579, DATA580, DATA581, DATA582, DATA583, DATA584, DATA585, DATA586, DATA587, DATA588, DATA589, DATA590, DATA591, DATA592, DATA593, DATA594, DATA595, DATA596, DATA597, DATA598, DATA599, DATA600, DATA601, DATA602, DATA603, DATA604, DATA605, DATA606, DATA607, DATA608, DATA609, DATA610, DATA611, DATA612, DATA613, DATA614, DATA615, DATA616, DATA617, DATA618, DATA619, DATA620, DATA621, DATA622, DATA623, DATA624, DATA625, DATA626, DATA627, DATA628, DATA629, DATA630, DATA631, DATA632, DATA633, DATA634, DATA635, DATA636, DATA637, DATA638, DATA639, DATA640, DATA641, DATA642, DATA643, DATA644, DATA645, DATA646, DATA647, DATA648, DATA649, DATA650, DATA651, DATA652, DATA653, DATA654, DATA655, DATA656, DATA657, DATA658, DATA659, DATA660, DATA661, DATA662, DATA663, DATA664, DATA665, DATA666, DATA667, DATA668, DATA669, DATA670, DATA671, DATA672, DATA673, DATA674, DATA675, DATA676, DATA677, DATA678, DATA679, DATA680, DATA681, DATA682, DATA683, DATA684, DATA685, DATA686, DATA687, DATA688, DATA689, DATA690, DATA691, DATA692, DATA693, DATA694, DATA695, DATA696, DATA697, DATA698, DATA699, DATA700) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    try{
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        String url = "jdbc:oracle:thin:@192.168.1.79:1521:nek";
	        String username = "app_incom";
	        String password = "intest";
	        connection = DriverManager.getConnection(url, username, password);
	        long start = System.currentTimeMillis();
	        prepareStatement = connection.prepareStatement(sql);
	        for(DataWareHouseV1 dataWareHouseV1: dataWareHouseV1s) {
	        	prepareStatement.setString(1, dataWareHouseV1.getSeqId());
	 	        prepareStatement.setInt(2, dataWareHouseV1.getTc());
	 	        prepareStatement.setTimestamp(3,  new Timestamp(dataWareHouseV1.getTe().getTime()));
	 	        prepareStatement.setString(4, dataWareHouseV1.getMd5());
	 	        prepareStatement.setDouble(5, dataWareHouseV1.getLongitude());
	 	        prepareStatement.setDouble(6, dataWareHouseV1.getLatitude());
	 	        prepareStatement.setDouble(7, dataWareHouseV1.getAltitude());
	 	        prepareStatement.setDouble(8, dataWareHouseV1.getSpeed());
	 	        prepareStatement.setDouble(9, dataWareHouseV1.getMileage());
	 	        prepareStatement.setDouble(10, dataWareHouseV1.getDirection());
	 	        List<Double> list = dataWareHouseV1.getVeliData();
	 	        int haveData = list.size();
	 	        for(int i = 0; i < haveData; i++) {
	 	        	prepareStatement.setDouble(i + 11, list.get(i));
	 	        }
	 	        for (int i = 0; i < 700 - haveData; i++) {
	 	        	prepareStatement.setNull(i + 11 + haveData, Types.INTEGER);
	 	        }
	 	        prepareStatement.addBatch();
	        }
	        prepareStatement.executeBatch();
	        connection.commit(); // 提交事务
	        long end  = System.currentTimeMillis();
	        System.out.println("用时:" + (end - start));
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            if (resultSet != null)
	            	resultSet.close();
	            if (prepareStatement != null)
	            	prepareStatement.close();
	            if (connection != null)
	            	connection.close();
	            System.out.println("connection is closed");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		return true;
	}
	
	public static List<DataWareHouseV1> Deduplication(List<DataWareHouseV1> list) {
		List<DataWareHouseV1> newList = new  ArrayList<DataWareHouseV1>(); 
        for (DataWareHouseV1 cd:list) {
           if(!newList.contains(cd)){
               newList.add(cd);
           }
       }
		return newList;
	}
}
