package ar.com.finit.owner.web.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ar.com.finit.dto.model.AlbumDTO;
import ar.com.finit.dto.model.ImageDTO;
import ar.com.finit.dto.model.factory.AlbumDTOFactory;
import ar.com.finit.dto.model.factory.ImageDTOFactory;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;

/**
 * @author leo
 */
@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageDTOFactory imageDTOFactory;
	@Autowired
	private AlbumDTOFactory albumDTOFactory;
	@Autowired
	private HttpProtocolHelper httpProtocolHelper;

	private static Logger logger = Logger.getLogger(ImageController.class);

	@RequestMapping(value = "/dialog/{album}", method = RequestMethod.GET)
	public ModelAndView imageDialog(@PathVariable String album) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("album", album);
		return new ModelAndView("dialog/imageDialog", params);
	}
	
	@RequestMapping("/{album}")
	public ModelAndView images(@PathVariable String album) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.ALBUM) + album;
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			
			AlbumDTO albumDTO = albumDTOFactory.makeDTO(response.getEntity());
			params.put("album", albumDTO);
		} else {
			params.put("error", response.getStatus());
		}

		return new ModelAndView("imageSlider", params);
	}

	@RequestMapping(value = "/upload/{album}", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, @PathVariable String album) {

		ImageDTO image = new ImageDTO();
		ClientResponse<String> response;
		String url;

		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		
		try {
			
			image.setExtension(mpf.getContentType());
			image.setName(mpf.getOriginalFilename());
			image.setEntity(album);
			
			byte[] data = this.scale(image, mpf.getBytes());
			
			url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.ALBUM) + album;
			response = httpProtocolHelper.getJsonRequest(url);
			if (response.getStatus() == Status.OK.getStatusCode()) {
			
				AlbumDTO albumDTO = albumDTOFactory.makeDTO(response.getEntity());
				if (albumDTO.getCover() != null) {
					albumDTO.addImage(image);
				} else {
					albumDTO.setCover(image);
					image.setCover(true);
				}
				url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.ALBUM) + album;
				response = httpProtocolHelper.postJsonRequest(url, albumDTO);
				if (response.getStatus() == Status.OK.getStatusCode()) {
					albumDTO = albumDTOFactory.makeDTO(response.getEntity());
					image = albumDTO.get(image.getName());
					externFile(data, image.getId(), image.getExtension());
					return imageDTOFactory.toJson(image);
				}
				
			}
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}

		throw new RuntimeException("ocurrio un error");

	}
	
	@RequestMapping(value = "/description/{image}", method = RequestMethod.POST)
	public @ResponseBody String saveDescription(@PathVariable String image, @RequestParam(value = "description", required = true) String description) {
		ClientResponse<String> response;
		String url;
		
		url = httpProtocolHelper.getServiceUrl(RestElement.PUB, RestElement.IMAGE) + image;
		response = httpProtocolHelper.getJsonRequest(url);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			ImageDTO imageDto = imageDTOFactory.makeDTO(response.getEntity());
			imageDto.setDescription(description);
			url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.IMAGE) + image;
			response = httpProtocolHelper.postJsonRequest(url, imageDto);
			if (response.getStatus() == Status.OK.getStatusCode()) {
				return "ok";
			}
		}
		return null;
	}
	

	private void externFile(byte[] data, String name, String extension) {
		try{
			InputStream in = new ByteArrayInputStream(data);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			File outputfile = new File(httpProtocolHelper.getPath(RestElement.IMAGE_ROOT) + name + "." + extension.split("/")[1]);
			ImageIO.write(bImageFromConvert, extension.split("/")[1], outputfile);
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
	
	private byte[] scale(ImageDTO image, byte[] data) {
    	try {
    		ByteArrayInputStream in = new ByteArrayInputStream(data);
    		BufferedImage img = ImageIO.read(in);
    		int height = img.getHeight();
			int width = img.getWidth();
    		
			if (height > 475) {
    			height = 475;
    			width = (height * img.getWidth())/ img.getHeight();
    		} 
    		if (width > 1095) {
    			width = 1095;
    			height = (width * img.getHeight())/ img.getWidth();
    		}
    		
			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    		BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
    		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    		ImageIO.write(imageBuff, image.getExtension().split("/")[1], buffer);
    		image.setHeight(height);
    		image.setWidth(width);
    		
    		return buffer.toByteArray();
    	
    	} catch (IOException e) {
    		throw new RuntimeException(e);
    	}
    }

}
