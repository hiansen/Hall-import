import junit.framework.Assert;

public class TestTest {
	@org.junit.Test
	public void deveIdentificarLinhasDeFonte() {
		Assert.assertTrue(Test.verificaLinhaFonte("Fonte:"));
		Assert.assertFalse(Test.verificaLinhaFonte("robson"));
	}
}
